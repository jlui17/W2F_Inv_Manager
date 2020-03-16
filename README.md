# My Personal CPSC 210 Project

## An Inventory Management System

The **Inventory Management System** is a program that will allow the user(s) to record stock for the 
games section of the *Richmond Night Market*. 

The user will be able to: 
- record stock bag information
- view a map of the storage containers 
- view logistical information regarding stock specfically for each game 

This program will mainly be used by myself, as I am working as the *Inventory Manager* for the *Richmond
Night Markets games section*. This project is interesting to me because there is no current tracking 
system in place, so this program will allow me to track all of our inventory and easily locate stock
that is needed on an operations night.

## User Stories

As a user, I want to be able to record a stock bag and its information into a storage container.  
This includes:  
- Description of stock  
- Bag number  
- Size of prize  
- Total quantity of stock in bag  
- Which game the stock is for  

As a user, I want to be able to view a map of a storage container.
		
As a user, I want to be able to move a stock bag from one container to another.

As a user, I want to be able to remove a stock bag from a container.

As a user, I want to be able to load the last save of containers on startup.

As a user, I want to be able to save each container whenever I click the save button.

## How to Use

As described previously, this program is a inventory management system that tracks what bags of prizes are stored in which 
container at the Richmond Night Market. The functions are very simple and can be used as follows:

The user has 2 different containers that stock bags can be stored into: containers A and B.

1. Record a stock bag
	This function creates a stock bag and stores it inside a container. To use it properly, you must enter a UNIQUE four-digit
	ID for the bag you're creating, and either "A or a" or "B or b" in the container field. The other fields can be any respective
	string or int.
	
2. Move a stock bag
	This function moves a stock bag from its current container to the other. As long as each bag has a UNIQUE ID, the function will
	find that bag, and if it's in container A it will be moved to container B, and vice versa.
	
3. Delete a stock bag
	This function completely removes a stock bag from any container. 
	
4. View container A
	This function displays a non-interactive map of container A. There can be up to 9 different bags in each container, and they're
	stored in an order like this:
	9|8|7
	6|5|4
	3|2|1
	
5. View container B
	This function is the same as "View container A" but displays container B.
	
6. Save
	This functions saves all containers and the stock bags inside them.
	
7. Load
	This function loads any previously saved containers and stock bags inside them back into the running program.
