# No.7 Meeting Minutes

2020/11/24  Tuesday

| **Time: 9:00-10:00**     | **Venue: PMB449**                         |
| :----------------------- | ----------------------------------------- |
| **Supervisor: Tony**     | **Attendance: 5 members except Yuan Dai** |
| **Chairman: Yixin Shen** | **Secretary: Yuxin Shi**                  |

### Agenda

**Current progress discussion**

**Q&A session**

**Advices for project**

**Plan for next week**



**Current progress discussion**

Improve prototype and add data filter interface.

New activity diagram

Report template and structure

Establish the database and import the simulation data to database



**Q&A session**

Q: If user don't need to select variables how to complete java inputs to python parameters? 

A: The model in python will access all inputs from java and it will select inputs automatically, different type of  data sets will have  different model.

Q: The filter data sets will apply to different models, and the model will need different variables, so we think user need to select......

A: These are two different things, variables need to filtering, and variables need to access model, these are separate functions.

Q: Model can decide what it needs, but the data from java are arraylists, so how to distinguish their differences?

A: Java only need to communicate with python and python will do these work

Q: What the python model will return?

A:That depends on you, you can return model itself.



**Advices for project**

For the UI Part, we don' t need to select variables because model will decide variables it needs automatically.

We can use a product type for the model, use different files to choose certain data.

For the data filtering , we need to select variables, but not all the variables just two or three key ones.

The model will specify the python side ,you don't need to pass data from java to python.

 For the report , you need other sections to describe formula, 

You can pass the filters to python, or use mySQL into java.



**Plan for next week**

Continue the work of last week

Start to write report

