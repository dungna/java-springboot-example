### DATA TYPES
There are 2 types data:
- Primitive Data Types: `byte, short, int, long, float, double, boolean, char`
- Reference/Object Data Types: `Java literals`

### LOOP
#### WHILE LOOP
```dtd
while(Boolean_expression) {
   // Statements
}
```
#### FOR LOOP
```
for(declaration : expression) {
    // Statements
}
```

#### DO...WHILE LOOP
```dtd
do {
   // Statements
} while(Boolean_expression);
```

## OOP
- Create new instance
<Class name> <name-instance> = new <Class name>()

EX:
`Main abc = new Main();`

- Accessing Instance Variables and Methods
```dtd
/* First create an object */
ObjectReference = new Constructor();

/* Now call a variable as follows */
ObjectReference.variableName;

/* Now you can call a class method as follows */
ObjectReference.MethodName();
```

- A Package can be defined as a grouping of related types (classes, interfaces, enumerations and annotations ) providing access protection and namespace management.
- Abstract class/method: 
  - **Abstract class**: is a restricted class that _cannot be used to create objects_ (to access it, it must be inherited from another class). 
  - **Abstract method**: can only be used in an abstract class, and it _does not have a body_. The body is provided by the subclass (inherited from).