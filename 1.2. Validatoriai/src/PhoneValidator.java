import java.util.*;
import java.io.*;

public class PhoneValidator 
{
    private boolean numberIsNullOrEmpty (String phoneNumber)
    {
        if(phoneNumber == null || phoneNumber.length() == 0)
            return true;
        else return false;
    }

    public boolean checkPhoneNumberForIllegalSymbols(String phoneNumber) 
    {
        if(numberIsNullOrEmpty(phoneNumber))
            return false;

        if(phoneNumber.charAt(0) == '+')
            phoneNumber = phoneNumber.substring(1);

        try
        {
            for(int i = 0; i < phoneNumber.length(); i++)
            {
                char c = phoneNumber.charAt(i);
                Integer.parseInt(Character.toString(c));
            }
        }
        catch (NumberFormatException ex) //if char cannot be parsed -> phone number contains illegal symbols
        {
            return false;
        }
        return true;
    }

    public String changePhoneNumberNationalPrefix(String phoneNumber) 
    {
        if(numberIsNullOrEmpty(phoneNumber) || phoneNumber.charAt(0) != '8')
            return phoneNumber;
        else return "+370"+ phoneNumber.substring(1);
    }

    public boolean checkPhoneNumber(String number)
    {
        return checkPhoneNumber(number, "LT"); //default value is LT
    }

    public boolean checkPhoneNumber(String number, String country)
    {
        if(numberIsNullOrEmpty(number))
            return false;

        Map<String, Integer>validationRules = getPrefixAndLength(country);
        if(validationRules.isEmpty())
            return false;

        for(Map.Entry<String, Integer> entry : validationRules.entrySet())
        {
            if(number.startsWith(entry.getKey()) && number.length() == entry.getValue())
                return true;
        }
            return false;
    }

    private Map<String, Integer> getPrefixAndLength(String country)
    {
        Map<String, Integer> values = new HashMap<>();
        try
        {
            File file = new File ("D:\\Users\\User\\Desktop\\ValidationRules.txt");
            Scanner scanner = new Scanner(file);

            while(scanner.hasNextLine())
            {
                String line =  scanner.nextLine();
                if(line.startsWith(country))
                {
                    line = line.substring(country.length()+1);
                    String prefix = line.substring(0, line.indexOf(" "));

                    line = line.substring(prefix.length()+1, line.length());
                    int length = Integer.parseInt(line);

                    values.put(prefix, length);
                }
            }
            scanner.close();
        }
        catch (Exception ex)
        {
            System.out.println("Failed to get validation rules! :(");
        }
        return values;
    }


    public void addNewValidationRule(String prefix, int length, String countryAbbreviaton)
    {
        try
        {
            String data = countryAbbreviaton + " " + prefix + " " + length + "\n";
            
            File file = new File("D:\\Users\\User\\Desktop\\ValidationRules.txt");

            if(!file.exists())
                file.createNewFile();

            FileWriter fileWriter = new FileWriter(file, true);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            bufferedWriter.write(data);
            bufferedWriter.close();
            fileWriter.close();
        }
        catch(Exception ex)
        {
            System.out.println("New validation rule was not created! :(");
        }
    }
}
