package flightRes;

import java.util.*;

public class ExceptionNotAvailable extends RuntimeException{
	public ExceptionNotAvailable(String msg)
	{
		super(msg); 
	}
}