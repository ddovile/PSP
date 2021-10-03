public class PasswordChecker 
{
    private char defaultSpecialSymbols[] = {'/', '*', '-', '+', '.', ',', '!'};
    private int defaultMinLength = 5;

    private boolean passwordIsNullOrEmpty(String pass)
    {
        if(pass == null || pass.length() == 0)
            return true;
        else return false;
    }
    public boolean checkPasswordLength (String pass)
    {
        return checkPasswordLength(pass, defaultMinLength);
    }

    public boolean checkPasswordLength(String pass, int minLen) 
    {
        if (passwordIsNullOrEmpty(pass))
            return false;

        if (pass.length() < minLen)
            return false;
        else return true;
    }

    public boolean checkPasswordForUppercase(String pass) 
    {
        if (passwordIsNullOrEmpty(pass))
            return false;

        for(int i = 0; i < pass.length(); i++)
        {
            char c = pass.charAt(i);
            if(Character.isUpperCase(c))
                return true;
        }
        return false;
    }

    public boolean checkPasswordForSpecialSymbols(String pass) 
    {
        return checkPasswordForSpecialSymbols(pass, defaultSpecialSymbols);
    }

    public boolean checkPasswordForSpecialSymbols(String pass, char[]specialSymbols) 
    {
        if (passwordIsNullOrEmpty(pass))
            return false;

        for(int i = 0; i <pass.length(); i++)
        {
            char c = pass.charAt(i);
            if(new String(specialSymbols).indexOf(Character.toString(c)) != -1 )
                return true;
        }
        return false;
    }
}
