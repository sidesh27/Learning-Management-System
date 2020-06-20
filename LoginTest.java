import java.util.*;

interface AccountCreator
{
	void CreateAccount() throws AccountLoginException;
	void displayAccount();
}

class Person 
{
	String name,ID,mail_id,username,password;

	static Scanner scanStr=new Scanner(System.in);
	static Scanner scanNum=new Scanner(System.in);

	static int t_count,s_count;

	static TreeSet<String> mailIDs= new TreeSet<String>();
	static TreeSet<String> personIDs= new TreeSet<String>();
	static TreeSet<String> subjectCodes= new TreeSet<String>();

	public static void initSubjectCode()
	{
		subjectCodes.add("UMA1101");
		subjectCodes.add("UCY1102");
		subjectCodes.add("UPH1103");
		subjectCodes.add("UGE1104");
		subjectCodes.add("UCS1105");
		subjectCodes.add("UEN1106");
	}

	static TreeSet<String> courses = new TreeSet<String>();
	
	public boolean validMailId()
    	{
    		int at=0,dot=0,dotPos=0,atPos=0;
    		for(int i=0;i<mail_id.length();i++)
    		{
    			if(mail_id.charAt(i) == '.')
    			{
    				dot++;
    				dotPos=i;
    			}
    			if(mail_id.charAt(i) == '@')
    			{
   	 			at++;
    				atPos=i;
    			}
	
    			if(at>1 || dot>1)
    				return false;
    		}		

    		if(at == 0 || dot == 0 || dotPos<atPos)
    			return false;
    		return true;
    	}
}

class MCQ
{
	String name;
	int no_q;
	String questions[]=new String[100];
	String options[][]=new String[100][4];
	int crctAns[]=new int[100];
	int stuAns[]=new int[100];
	double marks[]=new double[10];
	int c,i;
	char grade;
	static Scanner iobj=new Scanner(System.in);
	static Scanner sobj=new Scanner(System.in);
	
	void createTest()
	{	
		System.out.println("\nEnter no. of questions: ");
		no_q=iobj.nextInt();
		System.out.println("\n\t\tQuestions: ");
		for(int i=0;i<no_q;i++)
		System.out.println("\n\nEnter MCQ Name: ");
		name=sobj.nextLine();
		{
			
			System.out.println("\n\nEnter Question "+(i+1)+" :");
			questions[i]=sobj.nextLine();
			System.out.println("\n\t\tEnter options ");
			for(int j=0;j<4;j++)
			{
				System.out.print("\nOption"+(j+1)+" :");
				options[i][j]=sobj.nextLine();
				
			}
			
			System.out.print("\n\tEnter Option number of correct answer : ");
			crctAns[i]=iobj.nextInt();
		}
		
	}

	void attendTest()
	{
		for(int i=0;i<no_q;i++)
		{
			System.out.println("\nQuestion "+(i+1)+" :"+questions[i]);
			for(int j=0;j<4;j++)
			{
				System.out.println("   ("+(j+1)+")"+options[i][j]);
			}
			
			System.out.println("\nEnter choice: ");	
			while(true)
			{
				stuAns[i]=iobj.nextInt();
				if(stuAns[i]>0 && stuAns[i]<5)
					break;	
			}	
		}
	}
	
	void STUANS()
	{
		System.out.println("\n\tQuestions");
		int cr=0,wr=0;
		for(int i=0;i<no_q;i++)
		{  if(c<=0){
				c=0;
			  }
			System.out.println("\nQuestion "+(i+1)+" :");
			for(int j=0;j<4;j++)
			{
				System.out.println("   ("+(j+1)+")"+options[i][j]);
			}
			
			System.out.println("\n Correct Answer: "+crctAns[i]+" "+options[i][crctAns[i]-1]);
			System.out.println("\n Your answer: "+stuAns[i]+" "+options[i][stuAns[i]-1]);	

			if(crctAns[i]==stuAns[i])
			{
					System.out.println("\n\tCORRECT!!");
					cr++;
			}
			else
			{
					System.out.println("\n\tWRONG!!");
					wr++;
			}
		}
		//System.out.println("\nHEY : "+c+" "+(cr/no_q));
		marks[c++]=(double)(cr/no_q)*100/100.00;
		System.out.println("\nCORRECT ANSWERS :"+cr);
		System.out.println("\nWRONG ANSWERS   :"+wr);
		System.out.println("\nTOTAL           :"+marks[c-1]);
	}		
	
}

class Teacher extends Person implements AccountCreator
{
	String course;
	static int count;
	static boolean clear;

	int mcqCount;
	MCQ[] m =new MCQ[10];

	Teacher(int i)
	{
		mcqCount=i;
	}

	public void CreateAccount() throws AccountLoginException
	{
		InvalidMailIdException e1=new InvalidMailIdException();
		Person.initSubjectCode();
		
		System.out.println("------------- Creating New Account -------------\n");
			
		System.out.print("Enter name: ");
		name=scanStr.nextLine();
		
		while(true)
		{
			clear=true;
			try
			{
				System.out.print("Enter Employee ID: ");
				ID=scanStr.nextLine();
			
				if(t_count==0 && s_count==0)
					personIDs.add(ID);
				else
				{
					if(personIDs.contains(ID))
					{
						clear=false;
						throw new ExistingIdException(ID);
					}
					personIDs.add(ID);
					System.out.println(personIDs);
				}
			}
			catch(AccountLoginException e)
			{
				System.out.println(e);
			}
			finally
			{
				if(clear)
				break;
			}
		}
		
		while(true)
		{
			clear=true;

			try
			{
				System.out.print("Enter e-mail id (eg: abc@gmail.com) : ");
				mail_id=scanStr.nextLine();
				
				if(!validMailId())
				{
					clear=false;	
					System.out.println(e1);
					//System.out.println("Invalid format");
				}
				else if(t_count==0 && s_count==0)
					mailIDs.add(mail_id);
				else
				{
					if(mailIDs.contains(mail_id))
					{
						clear=false;
						throw new ExistingMailIdException(mail_id);
					}
					mailIDs.add(mail_id);
				}
			}
			catch(ExistingMailIdException e)
			{
				System.out.println(e);
			}
			finally
			{
				if(clear)
					break;
			}
		}
	
		System.out.print("Enter password: ");
		password=scanStr.nextLine();
		
		while(true)
		{
			clear=true;
			System.out.println(subjectCodes);
			try
			{
				System.out.println("\n1.MATHEMATICS - UMA1101\n2.ENGINEERING CHEMISTRY - UCY1102");
				System.out.println("3.ENGINEERING PHYSICS - UPH1103\n4.ENGINEERING GRAPHICS - UGE1104");
				System.out.println("5.PROGRAMMING IN PYTHON - UCS1105\n6.TECHNICAL ENGLISH - UEN1106\n");
				System.out.print("Enter subject code for the course handled: ");
				course=scanStr.nextLine();
				
				if(!subjectCodes.contains(course))
				{
					clear=false;
					throw new InvalidCourseException(course);
				}

				if(count==0)
					courses.add(course);
				else
				{
					if(courses.contains(course))
					{
						clear=false;
						throw new UnavailableCourseException(course);
					}
				}
			}
			catch(AccountLoginException e)
			{
				System.out.println(e);
			}
			finally
			{
				if(clear)
					break;
			}
		}

		System.out.println("\n--------- Account successfully created ---------\n");

		this.count++;
		t_count=this.count;
	}

	public void displayAccount()
	{
		System.out.println("------------- TEACHER PROFILE -------------\n");
//		System.out.println("Name: "+ name +"\nTeacher ID:" + ID +"\nUsername:" + username +"\ne-mail id: "+ mail_id +"\nCourse: "+course);
		System.out.println("Name: "+ name +"\nTeacher ID:" + ID +"\ne-mail id: "+ mail_id +"\nCourse: "+course);
		System.out.println("\n-------------------------------------------\n\n");
	}

	public Teacher editAccount(Teacher t)
	{
		int ch;
		String old_pw,new_pw;
		while(true)
		{
			System.out.println("\n1.Edit password\n2.Edit Name\nEnter choice(0 to return to Home Page):");
			ch=scanNum.nextInt();
			switch(ch)
			{
				case 0:
					return t;

				case 1:
				
					System.out.println("\nEnter previous password: ");
					old_pw=scanStr.nextLine();

					if(old_pw.equals(t.password))
					{
						System.out.println("Enter new password: ");
						t.password=scanStr.nextLine();
						System.out.println("\n-----Password successfully changed-----\n");
					}
					else
						System.out.println("Invalid password");
					break;

				case 2:
					System.out.println("\nEnter new Name: ");
					t.name=scanStr.nextLine();
					System.out.println("\n-----Username successfully changed-----\n");
					break;

				default:
					return t;
			}
		}
	}

	public Teacher login(String mail,String pw,Teacher T[])
	{
		int i;
		Teacher t= new Teacher(0);

		for(i=0;i<T.length;++i)
			if(mail.equals(T[i].mail_id) && pw.equals(T[i].password))
				return T[i];	
		
		return t;
	}

	public Teacher[] Home(Teacher T[],Teacher t)
	{
		int op;
		System.out.println("\n------------ Teacher Home Page ------------\n");
		while(true)
		{
			System.out.println("1.Create MCQ\n2.View Profile\n3.Edit Profile");
			System.out.println("Enter choice(0 to return to home page): ");
			op=scanNum.nextInt();
			switch(op)
			{
				case 0:
					return T;
				case 1:
					t.m[t.mcqCount]=new MCQ();
					t.m[t.mcqCount].createTest();
					t.mcqCount++;
					break;

				case 2:
					t.displayAccount();
					break;

				case 3:
					t=t.editAccount(t);
					break;

				default:
					return T;
			}
		}
	}
}

class Student extends Person implements AccountCreator
{
	String dept;
	static int count;
	static boolean clear;

	Teacher[] tea=new Teacher[10];
	
	void initTeacher(Teacher t[])
	{
		tea=t;
	}

	public void CreateAccount() throws AccountLoginException
	{
		InvalidMailIdException e1=new InvalidMailIdException();
		System.out.println("------------- Creating New Account -------------\n");

		System.out.print("Enter name: ");
		name=scanStr.nextLine();

		while(true)
		{
			clear=true;
			try
			{
				System.out.print("Enter Student ID: ");
				ID=scanStr.nextLine();

				if(t_count==0 && s_count==0)
					personIDs.add(ID);
				else
				{
					if(personIDs.contains(ID))
					{
						clear=false;
						throw new ExistingIdException(ID);
					}
					personIDs.add(ID);
				}
				
			}
			catch(AccountLoginException e)
			{
				System.out.println(e);
			}
			finally
			{
				if(clear)
					break;
			}
		}
		
		while(true)
		{
			clear=true;
			try
			{
				System.out.print("Enter e-mail id (eg: abc@gmail.com) : ");//handle existing mail-id exception
				mail_id=scanStr.nextLine();	
				
				if(!validMailId())
				{
					clear=false;
					System.out.println(e1);	
					//System.out.println("Invalid format");
				}
				else if(t_count==0 && s_count==0)
					mailIDs.add(mail_id);
				else
				{
					if(mailIDs.contains(mail_id))
					{
						clear=false;
						throw new ExistingMailIdException(mail_id);
					}
					mailIDs.add(mail_id);
				}
			}
			catch(AccountLoginException e)
			{
				System.out.println(e);
			}
			finally
			{
				if(clear)
					break;
			}
		}

		System.out.print("Enter password: ");
		password=scanStr.nextLine();
		System.out.print("Enter department: ");
		dept=scanStr.nextLine();
		
		System.out.println("\n--------- Account successfully created ---------\n");

		this.count++;
		s_count=this.count;
	}

	public void displayAccount()
	{
		System.out.println("------------- STUDENT PROFILE --------\n");
//		System.out.println("Name: "+ name +"\nStudent ID:" + ID +"\nUsername:" + username +"\ne-mail id: "+ mail_id +"\nDepartment: "+dept);
		System.out.println("Name: "+ name +"\nStudent ID:" + ID +"\ne-mail id: "+ mail_id +"\nDepartment: "+dept);
		System.out.println("\n--------------------------------------\n\n");
	}

	public Student editAccount(Student s)
	{
		int ch;
		String old_pw,new_pw;
		while(true)
		{
			System.out.println("\n1.Edit password\n2.Edit Name\nEnter choice(0 to return to Home Page):");
			ch=scanNum.nextInt();
			switch(ch)
			{
				case 0:
					return s;

				case 1:
				
					System.out.println("\nEnter previous password: ");
					old_pw=scanStr.nextLine();

					if(old_pw.equals(s.password))
					{
						System.out.println("Enter new password: ");
						s.password=scanStr.nextLine();
						System.out.println("\n-----Password successfully changed-----\n");
					}
					else
						System.out.println("Invalid password");
					break;

				case 2:
					System.out.println("\nEnter new Name: ");
					s.name=scanStr.nextLine();
					System.out.println("\n-----Username successfully changed-----\n");
					break;

				default:
					return s;
			}
		}
	}

	public Student login(String mail,String pw,Student S[])
	{
		int i;
		Student empty=new Student();

		for(i=0;i<S.length;++i)
			if(mail.equals(S[i].mail_id) && pw.equals(S[i].password))
				return S[i];

		return empty;
	}

	public Student[] Home(Student S[],Student s)
	{
		int op,p,l,i;
		System.out.println("\n------------ Student Home Page ------------\n");
		while(true)
		{
			System.out.println("1.Attempt MCQ\n2.View Profile\n3.Edit Profile");
			System.out.println("Enter choice(0 to return to Home Page):: ");
			op=scanNum.nextInt();
			switch(op)
			{
				case 0:
					return S;
				case 1:

					System.out.println("\tSUBJECTS ");
					for(int y=0;y<6;y++)
					{
						if(tea[y].course!=null)
						System.out.println(" "+(y+1)+"."+tea[y].course);
					}
					System.out.println("Enter Subject Number: ");
					p=scanNum.nextInt();
					for(int k=0;k<tea[p-1].mcqCount;k++)
					
					{
						//if(tea[p-1].m[k].name != null)
						System.out.println((k+1)+". "+tea[p-1].m[k].name);
					}
					
					System.out.print("\n Enter Test No:");
					l=scanNum.nextInt();
					s.tea[p-1].m[l-1].attendTest();
					s.tea[p-1].m[l-1].STUANS();
					for(int o=0;o<(s.tea[p-1].m[l-1].c);o++){
						System.out.println("You have a : "+(s.tea[p-1].m[l-1].marks[o])*100+"% IN TEST "+ (o+1));
					}	
					System.out.println("\n You have attended :"+s.tea[p-1].m[l-1].name);
					break;
			
				case 2:
					s.displayAccount();
					break;

				case 3:
					s=s.editAccount(s);
					break;

				default:
					return S;
			}
		}	
	}
}

public class LoginTest
{
	static Scanner scanStr=new Scanner(System.in);
	static Scanner scanNum=new Scanner(System.in);

	public static void main(String[] args) throws AccountLoginException
	{
		int i,ch1,ch2;

		Teacher T[]= new Teacher[6];
		Teacher t=new Teacher(0);
		Student S[]= new Student[10];
		Student s=new Student();

		Teacher t1=new Teacher(0);
		Student s1=new Student();

		for(i=0;i<T.length;++i) 
			T[i]=new Teacher(0);

		for(i=0;i<S.length;++i)
			S[i]=new Student();

		String mailID,pw;
		boolean valid;

		while(true) 
		{
			System.out.println("------------- THE LEARN ZONE ------------\n");
			System.out.println("Welcome to THE LEARN ZONE-LMS.\nTo proceed with, Are you a Teacher/Student?");
			System.out.print("Please enter 1 for Teacher and 2 for Student(0 to quit): ");

			ch1=scanNum.nextInt();	

			switch(ch1)
			{
				case 0:
					System.exit(1);
				case 1:

					while(true)
					{
						System.out.println("\n------------ TEACHER LOGIN ------------\n");
						System.out.println("Are you a new user/existing user?\nPlease enter 1 for new user and 2 for existing user(any number to return to Home Page): ");
						ch2=scanNum.nextInt();	
						if(ch2==1)
							T[t.count].CreateAccount();
						else if(ch2==2)
						{
							System.out.println("Enter e-mail id: ");
							mailID=scanStr.nextLine();
							System.out.println("Enter password: ");
							pw=scanStr.nextLine();
							t1=t.login(mailID,pw,T);
							if(t1.name!=null)
							{
								System.out.println("Valid Login. Redirecting to Teacher Home Page");

								T=t.Home(T,t1);
							}
							else
								System.out.println("Invalid Login");
						}
						else
							break;
						/*for(i=0;i<T[0].count;++i)
							T[i].displayAccount();*/
					}
					
					break;

				case 2:

					while(true)
					{
						System.out.println("\n------------ STUDENT LOGIN ------------\n");
						System.out.println("Are you a new user/existing user?\nPlease enter 1 for new user and 2 for existing user(any number to return to Home Page): ");
						ch2=scanNum.nextInt();	
						if(ch2==1)
							S[s.count].CreateAccount();
						else if(ch2==2)
						{
							System.out.println("Enter e-mail id: ");
							mailID=scanStr.nextLine();
							System.out.println("Enter password: ");
							pw=scanStr.nextLine();
							s1=s.login(mailID,pw,S);
							if(s1.name!=null)
							{
								System.out.println("Valid Login. Redirecting to Student Home Page");
																  									s.initTeacher(T);
								for(int k=0;k<s.count;k++)
								{
							 		S[k].initTeacher(T);
								 
								}
								S=s.Home(S,s1);
							}
							else
								System.out.println("Invalid Login");
						}
						else
							break;
						/*for(i=0;i<S[0].count;++i)
							S[i].displayAccount();*/
					}
				
					break;
			}
		}
	}
}
