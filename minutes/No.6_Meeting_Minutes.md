# No.6 Meeting Minutes 



2020/11/17  Tuesday

| Time: 9:00-10:00            | Venue: PMB449               |
| --------------------------- | --------------------------- |
| **Supervisor: Tony**        | **Attendance: All members** |
| **Chairman: YiCheng Zhang** | **Secretary: Ruibin Chen**  |

### Agenda

1. show progress

2. plan

3. Q&A

4. suggestions

5. lesson

   

**Current progress discussion**

1. UML diagram
2. prototype GUI design 
3. transform runnable model into python
4. system: Java to Python, using runtime method



**Plan for next week**

1. Modify the UI design
2. Implement Java  visualization



**Q&A session**

Q: Is system need to be able to achieve different lag on every columns or just on types like MV,BV?

A: Not every variable makes sense to have lags. In the real world, it will be a big discussion. Can assume that the client will provide data already right.

Q: Have confusions on filter. How to filter from database?

A: When considering filter, I think about different *partition of data*, such as US data or UK one. Transforming is different from filtering. For this state, only consider locations in filters.

Q: In the end of this semester, should we need to produce a small software to run.

A: Yes, that's why we want to do rapid development. Get feedback. 

Q: Do we need to sensor the useless data or null value?

A: The data given is OK, not too bad. In real world data, yes.  



**Suggestion**

Start agile development or rapid development. Can get feedback from client, which is really important. Especially for visualization, don't know exactly the client want, so needs feedback. 

Suggest to apply batch in system frame, to improve efficiency of P function. Because the data set is quiet huge and java to python costs. 

The activity diagram needs to be able to go back, e.g. choose the wrong model and need to choose the model again. Considering the human mistakes. 

For UI part, some windows of operations need to be modified.  The column choosing part is redundant. It is good to show the information of data, but it is client's job to choose variables and offer right model. 

When load model, small boxes for users to fill in can be displayed on screen. 

Separate the team in to different partitions and don't forget to help each other. 

