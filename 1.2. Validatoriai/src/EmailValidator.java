public class EmailValidator 
{
    private boolean emailIsNullOrEmpty(String email)
    {
        if(email == null || email.length() == 0)
            return true;
        else return false;
    }
    public boolean checkForAtSymbol(String email) 
    {
        if(emailIsNullOrEmpty(email))
            return false;
        
        if(email.indexOf("@") == -1)
            return false;
        else return true;
    }

    public boolean checkForIllegalSymbols(String email) 
    {
        int index = email.indexOf("@");
        for(int i = 0; i < index; i++)
        {
            char c = email.charAt(i);
            if(c != '.' && !Character.isLetter(c))
                return false;
        }
        return true;
    }

    public boolean checkDomainAndTLD(String email) 
    {
        String domain = email.substring(email.indexOf("@")+1);
        String tdl = email.substring(email.lastIndexOf('.')+1);

        if(tdl.length() < 2)
            return false; //tdl - at least 1 char
        if(domain.length() < 3)
            return false; //domain - at least 2 char

        for(int i = 0; i < domain.length(); i++) //valid for domain: A-Z, a-z, 0-9 and '-'
        {
            char c = domain.charAt(i);
            if(!Character.isLetter(c) && !Character.isDigit(c))
            {
                if(c == '-' ||  c == '.') //if symbol '-'or '.' are the last one or the first one character in the domain
                {
                    if(i == 0 || i == domain.length()-1)
                        return false;
                }
                else return false;
            }
        }

        return true;
    }
}
