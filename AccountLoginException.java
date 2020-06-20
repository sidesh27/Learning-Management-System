
class AccountLoginException extends Exception
{
	public String toString()
	{
		return "\nException : AccountLoginException\n";
	}
}
class ExistingMailIdException extends AccountLoginException
{
	String mailID;

	ExistingMailIdException(String mailID)
	{
		this.mailID=mailID;
	}
	public String toString()
	{
		return "\nException : ExistingMailIdException \nThe e-mail ID - " + mailID + " already exists\n";
	}
}
class ExistingIdException extends AccountLoginException
{
	String ID;
	ExistingIdException(String ID)
	{
		this.ID=ID;
	}
	public String toString()
	{
		return "\nException : ExistingIdException \nThe ID - " + ID + " already exists\n";
	}
}

class InvalidCourseException extends AccountLoginException
{
	String course;
	InvalidCourseException(String course)
	{
		this.course=course;
	}
	public String toString()
	{
		return "\nException : InvalidCourseException \nThe course code - " + course + " doesnt exist\n";
	}
}

class UnavailableCourseException extends AccountLoginException
{
	String course;
	UnavailableCourseException(String course)
	{
		this.course=course;
	}
	public String toString()
	{
		return "\nException : UnavailableCourseException \nChosen course " + course + " is already handled by a teacher\n";
	}
}
class InvalidMailIdException extends AccountLoginException
{
	
	public String toString()
	{
		return "\nException : InvalidMailIdException \nmailID has an invalid format\n";
	}
}
