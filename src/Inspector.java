/*******************
* Adrian Cristea
* CPSC 501 Assignment 1
* Version 1.1
*******************/
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.lang.reflect.TypeVariable;

public class Inspector {
	public static void inspect(Object obj, boolean recursive) throws Exception {
		
		//Class Handlers
		System.out.println("***** CLASS *****");
		
		//setting meta CLASS object classObj
		Class classObj = obj.getClass();
		
		//printing out declaring class name 
		System.out.println("CLASS NAME: " + classObj.getName());
		
		
		//printing out name of immediate superclass
		System.out.println("IMMEDIATE SUPERCLASS NAME: " + classObj.getSuperclass());
		
		//printing out name of the interfaces the class implements
		Class [] interfaces = classObj.getInterfaces();
		int lengthInterfaces = interfaces.length;
		for (int i =0; i < lengthInterfaces; i++)
		{
		  System.out.println("INTERFACE " + i + " : " + interfaces[i]);
		}
			
		//Methods Handlers
		System.out.println("***** METHODS *****");
		
		
		//setting meta METHOD object classMethods
		Method [] objMethods = classObj.getDeclaredMethods();;

		//printing out the methods the class declares
		for (int i = 0; i < objMethods.length; i++)
		{
		  System.out.println("METHOD " + i + " : " + objMethods[i]);
		  Class [] typeException = objMethods[i].getExceptionTypes();
		  for(int j = 0; j < typeException.length; j++) {
			//printing out the exceptions thrown by the methods
			  System.out.println("THROWN EXCEPTIONS: " + typeException[j].toString());
		  }
		  
		  Class [] parametersObj =  objMethods[i].getParameterTypes();
		  for(int j = 0; j < parametersObj.length; j++) {
				//printing out the exceptions thrown by the method
				System.out.println("PARAMATER TYPES: " + parametersObj[j].toString());
		  }
		  //printing out the return type of the method
		  Class returnTypeObj =  objMethods[i].getReturnType();
		  System.out.println("RETURN TYPE: " + returnTypeObj.toString());
		  //printing out the modifiers of the method
		  int methodModifiers = objMethods[i].getModifiers();
		  System.out.println("MODIFIER: " + Modifier.toString(objMethods[i].getModifiers()));
		  System.out.println("MODIFIERS: " + methodModifiers);
		}	
		
		//Constructor Handlers
		System.out.println("***** CONSTRUCTOR *****");
		//setting meta CONSTRUCTOR object objectConstructor
		Constructor [] objectConstructor = classObj.getDeclaredConstructors();		
		for (int i = 0; i < objectConstructor.length; i++) {
			
			//printing out the Constructor Name
			System.out.println("CONSTRUCTOR: " + objectConstructor[i].toString());
			TypeVariable[] variableParameterTypes = objectConstructor[i].getTypeParameters();
			for(int j = 0; i < variableParameterTypes.length; j++) {
				//printing out the parameter type of the constructor
				System.out.println("PARAMETER TYPE: " + variableParameterTypes[j].toString());
			}
			int constructorModifers = objectConstructor[i].getModifiers();
			//printing out the modifiers of the constructor
			System.out.println("MODIFIER: " + Modifier.toString(objectConstructor[i].getModifiers()));
			System.out.println("MODIFIERS: " + constructorModifers);
		}	
			
		//Field Handlers
		System.out.println("***** FIELD *****");

		//setting meta FIELD object objectField
		Field [] objectField =  classObj.getDeclaredFields();
		
		for(int i = 0; i < objectField.length; i++) {
			objectField[i].setAccessible(true);
			//printing out FIELD NAME of the field
			System.out.println("FIELD NAME: " + objectField[i].getName());
			//printing out type of the field 
			Class typeObject = objectField[i].getType();
			System.out.println("TYPE: " + typeObject.toString());
			//printing out the modifiers of the field
			int fieldModifiers = objectField[i].getModifiers();
    		System.out.println("MODIFIER: " + Modifier.toString(objectField[i].getModifiers()));
			System.out.println("MODIFIERS: " + fieldModifiers);
		}
	
	}

}