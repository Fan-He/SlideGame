# SlideGame

Title: 2048 Slide Game

Author: Xiaofan He

Description: 
------------
This is a 2048 slide game that the user can slide both vertically/horizontally and diagnally. 

When the user presses one of the four buttons in the cornors, all number grids slide to the direstion of the pressed button, which are up-left, up-right, down-left, down-right. 
When the user presses a button on the first row, all number grids slide up; when the user presses a button on the buttom row, all number grids slide down. 
When the user presses a button on the left most column, all number grids slide left; when the user presses a button on the right most column, all number grids slide right. 
For example: [0 1 0 2] after sliding left would be [1 2 0 0], after sliding right would be [0 0 1 2]. 

Same numbers will add together if they collide when sliding. 
For example: [0 1 0 1] will become [2 0 0 0] after sliding left, [0 0 0 2] after sliding right
The merging follows the direction of sliding, for example, [0 1 1 2 0] will become [4 0 0 0 0] after sliding left, [0 0 0 2 2] after sliding right. 
Same rules for all eight directions. 

The project uses JavaFx, implementing Application. 

In future improvements, UI will be improved and the number of rows and columns will be able to adjust based on the player's choices. 




How to install and run: 
-----------------------
To install and run the project, first download the SlideGame.java, then put it in a project and run it. The game will launch after running. 




Credits: 
--------
Xiaofan He
