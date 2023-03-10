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

For a quick tutorial on how to run the script click
* [mysql workbench]() 
* [command line]() 

If the initialization is succussful, the tables created are \

<img width="184" alt="tables" src="https://user-images.githubusercontent.com/29023871/223933109-4aa5166e-c9c7-4753-adc5-869d249352fd.png">

### Running the MySQL Script

Open MySQL Workbench -> Open your root/local instance account -> Server -> Data Import 
Once you open the Data Import page, the settings should look like this: 
![image](https://user-images.githubusercontent.com/71765888/224203661-a34fe2b9-d202-4cf7-8074-cdf47c49702c.png)
The path to the libraryDB.sql should be changed to the path to the file in your repository
Once all the settings are the same, hit the "Start Import" button

### Running The Project

Now only thing to do is to open Eclipse -> LibraryPorject -> src -> GUI -> LibraryUI.java 

<img width="294" alt="one" src="https://user-images.githubusercontent.com/29023871/223938233-71200c8b-62a3-4b7d-93af-184544089c5d.png"> 
<img width="711" alt="two" src="https://user-images.githubusercontent.com/29023871/223938312-debd4ac7-ed46-4206-b311-77dc8ab5febe.png"> 




## Current Roadmap

- [ ] Search Functionality
- [ ] Login System
- [ ] Checking Out/Holding Books
- [ ] Book Recommendations
- [ ] Rate and Review Books





