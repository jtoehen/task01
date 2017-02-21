package co.jtoehen.exception;

/**
 * Created by jtoehen on 21/2/2017.
 */
public class AtmException extends Exception
{
    private static final long serialVersionUID = 1L;
    private String errorMessage;

    public String getErrorMessage()
    {
        return errorMessage;
    }

    public AtmException(String errorMessage)
    {
        super(errorMessage);
        this.errorMessage = errorMessage;
    }

    public AtmException()
    {
        super();
    }
}