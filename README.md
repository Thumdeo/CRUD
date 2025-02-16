I am Dumb

Implementing Springboot security:
1.Implementing basic Registeration.
2.Implementing basic Authentication.
3.Implemented basic custom validator.


Tutorial or guide :- https://spring.io/guides

localhost server request format: http://localhost:9090/blogger/get/blogger


Latest Json request body format for making request in postman:
{
  "userName": "John Doe",
  "title": "My Blog Title",
  "blogPost": "This is a blog post content.",
  "blogComment": "Interesting post!",
  "blogLikes": "50",
  "blogDislike": "5"
}

********************************************************////////////////////////////////////////////////**********************************************************

# ****set up Maven intellij IDEA**

**

# A . _Use Maven in IntelliJ IDEA_

1. Open IntelliJ IDEA.
2. Go to File > Settings > Build, Execution, Deployment > Build Tools > Maven.
3. Set the Maven Home Directory:
4. Choose Custom and provide the path to your Maven installation (e.g., C:\Program Files\Apache\Maven).
5. (IMP) Alternatively, choose Bundled Maven (version x.x) to use IntelliJ's built-in Maven.
6. Apply and reload the Maven project:
7. Open the Maven Tool Window (on the right side of IntelliJ) and click Reload All Maven Projects.

OR 

# _B . Use Maven in IntelliJ IDEA using file setup_
1.  Extract the ZIP File
    Locate the downloaded maven-x.x.x-bin.zip file.
    Extract it to a directory on your computer (e.g., C:\Program Files\Maven or D:\Tools\Maven).
2.  Set Environment Variables
    You need to set Maven’s bin folder in the system's PATH to make it globally accessible.

On Windows:
Open Environment Variables:

Right-click This PC → Properties → Advanced System Settings → Environment Variables.
Set MAVEN_HOME:

In System Variables, click New.
Variable Name: MAVEN_HOME
Variable Value: Path to the extracted Maven folder (e.g., C:\Program Files\Maven\apache-maven-x.x.x).
Add to PATH:

Find the Path variable in System Variables, select it, and click Edit.
Add: %MAVEN_HOME%\bin.
Save and Close:

Click OK to save all changes.

*******************************************************************************************************************************
********************///////////////////////////////////////////////////////////////////////////////////////////************************************************


# **Custom Validators:**

Custom Validators are used to make sure that the input taken are to be valid by the latest preferences.

## __Email Validator:__ 

Despite already available email validators there are some important reasons for using custom email validator.
Being hiberante email validator it has some old refernces which are not suitable for present requirements, additionally 
to ensure we can customize our email field suitable according to our requirement.
EX:we’re rolling our own custom annotation instead of Hibernate’s 
@Email because Hibernate considers the old intranet addresses format,
myaddress@myserver, as valid (see Stackoverflow article), which isn’t good.

(Reference: Baeldong.)




