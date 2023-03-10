# BookMate  
> Created by: Abbey, Isaiah, Jet, Sathira and Vince

## What is BookMate?

BookMate is a library system and social media hybrid app that is focused on students and book lovers alike. 
Similar to an actual library website, users can see what books are available to borrow, place holds, and check the status 
of the books they’ve checked out or placed holds on.  BookMate's social media component allows users to rate and review books to build their profile, and interact with other BookMates.


### Preview 

<img width="1552" alt="Preview" src="https://user-images.githubusercontent.com/29023871/223938698-a344e7a5-443b-48a8-9fa1-4a625d8ebae7.png">

***



# Set Up

To run this project, there are a dew things that needs to be set up first. 
* Initialize databse 
* Configure mysql password 

### Initialize Databse
To initialize the database, please  download and run the databse script named 'libraryDB' as seen in the screen shot below. 

<img width="1433" alt="screnshot1" src="https://user-images.githubusercontent.com/29023871/223929924-3e5131cf-e577-4bf5-bce8-237989001d87.png">

Import the sql file into the workbench and run. Or run it from the command line. 

If the initialization is succussful, the tables created are

![SCR-20230310-ep3](https://user-images.githubusercontent.com/29023871/224357984-bc88d91d-e2e2-4e8b-832b-681a61ae1330.png)


### Running the MySQL Script

Open MySQL Workbench -> Open your root/local instance account -> Server -> Data Import 
Once you open the Data Import page, the settings should look like this: 
![image](https://user-images.githubusercontent.com/71765888/224203661-a34fe2b9-d202-4cf7-8074-cdf47c49702c.png)
The path to the libraryDB.sql should be changed to the path to the file in your repository. \
Once all the settings are the same, hit the "Start Import" button.

### Running The Project

Now only thing to do is to open Eclipse -> LibraryPorject -> src -> GUI -> LibraryUI.java 

<img width="294" alt="one" src="https://user-images.githubusercontent.com/29023871/223938233-71200c8b-62a3-4b7d-93af-184544089c5d.png"> 
<img width="711" alt="two" src="https://user-images.githubusercontent.com/29023871/223938312-debd4ac7-ed46-4206-b311-77dc8ab5febe.png"> 

The console will appear with a prompt asking you to enter your MySQL password.  Once you enter your password, the actual BookMate application will open. 

![image](https://user-images.githubusercontent.com/71765888/224206680-602781a0-adf1-440b-83d3-cfe20a307b50.png) \
Hit the profile icon on the bottom left of the sidebar to create an account.
![image](https://user-images.githubusercontent.com/71765888/224206807-1e171f43-285b-4b60-95df-be2edba57f0a.png) \
Enter your account information, hit "Register", and then you will be taken to your profile. \
![image](https://user-images.githubusercontent.com/71765888/224207200-0348f818-08d9-4794-8813-baf61271674a.png)
![image](https://user-images.githubusercontent.com/71765888/224207238-562a1ee1-4a2d-496f-9e9a-74a31576536c.png) \
If you click on the home button icon, you will see a list of books that you can open or search for in the search bar on the top right.
![image](https://user-images.githubusercontent.com/71765888/224207413-a13945aa-5c33-47c3-a94a-010c4bc63a7e.png) 

## Current Roadmap

- [ ] Search Functionality
- [ ] Login System
- [ ] Checking Out/Holding Books
- [ ] Book Recommendations
- [ ] Rate and Review Books





