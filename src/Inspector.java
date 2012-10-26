/*******************
 * Adrian Cristea
 * CPSC 501 Assignment 1
 * Does reflection on a certain invoked object (obj)
 * Version 1.3
 *******************/
import java.lang.reflect.Array;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Member;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.lang.reflect.TypeVariable;
import java.util.Arrays;

public class Inspector {

	//Initiates inspect method which will trigger the reflection methods accordingly	
	public String inspect(Object obj, boolean recursive) throws Exception {

		Class classObj = obj.getClass();

		classInspector(obj);			

		methodInspector(classObj, obj);

		constructorInspector(classObj, obj);

		fieldInspector(classObj, obj);

		if(recursive == true) {
			System.out.println("TRAVERSING CLASSES: ");
			traverseSuperClasses(classObj.getSuperclass());
			System.out.println("DONE TRAVERSING");
			return "true";			
		}
		return "false";
	}
	//inspects class object and prints out accordingly
	public static void classInspector(Object anObject) throws Exception {

		//setting meta CLASS object classObj
		Class classObj = anObject.getClass();

		//Class Handlers
		System.out.println("***** CLASS *****");

		//printing out declaring class name 
		System.out.println("CLASS NAME: " + classObj.getClass().getSimpleName());
		//printing out name of immediate superclass
		System.out.println("SUPERCLASS NAME: " + classObj.getSuperclass().getSimpleName());

		//printing out the traversing classes

		System.out.println("IMMEDIATE SUPERCLASS NAME: " + classObj.getSuperclass().getSimpleName());

		//printing out name of the interfaces the class implements
		Class [] interfaces = classObj.getInterfaces();

		int lengthInterfaces = interfaces.length;
		for (int i = 0; i < lengthInterfaces; i++)
		{
			System.out.println("INTERFACE " + i + " : " + interfaces[i].getSimpleName());

		}
	}
	//inspects method object and prints out accordingly
	public static void methodInspector(Class classObj, Object anObject) {

		//setting meta METHOD object classMethods
		Method [] methodsObj = classObj.getDeclaredMethods();

		//Methods Handlers
		System.out.println("***** METHODS *****");

		//printing out the methods the class declares
		for (int i = 0; i < methodsObj.length; i++)
		{

			System.out.println("METHOD " + i + " : " + methodsObj[i]);
			Class [] typeException = methodsObj[i].getExceptionTypes();
			for(int j = 0; j < typeException.length; j++) {
				//printing out the exceptions thrown by the methods
				System.out.println("THROWN EXCEPTIONS: " + typeException[j].toString());
			}

			Class [] parametersObj = methodsObj[i].getParameterTypes();
			for(int j = 0; j < parametersObj.length; j++) {
				//printing out the exceptions thrown by the method
				System.out.println("PARAMATER TYPE: " + parametersObj[j].toString());
			}
			//printing out the return type of the method
			Class returnTypeObj =  methodsObj[i].getReturnType();
			System.out.println("RETURN TYPE: " + returnTypeObj.toString());
			//printing out the modifiers of the method
			int methodModifiers = methodsObj[i].getModifiers();
			System.out.println("MODIFIER: " + Modifier.toString(methodsObj[i].getModifiers()));
			System.out.println("MODIFIERS: " + methodModifiers);
		}	

	}
	//inspects constructor object
	public static void constructorInspector(Class classObj, Object anObject) {

		//setting meta CONSTRUCTOR object objectConstructor
		Constructor [] constructorObj = classObj.getDeclaredConstructors();

		//Constructor Handlers
		System.out.println("***** CONSTRUCTOR *****");

		for (int i = 0; i < constructorObj.length; i++) {

			//printing out the Constructor Name
			System.out.println("CONSTRUCTOR: " + constructorObj[i].toString());
			Class [] parametersObj = constructorObj[i].getParameterTypes();
			for(int j = 0; j < parametersObj.length; j++) {
				//printing out the parameter type of the constructor
				System.out.println("PARAMETER TYPE: " + parametersObj[j].toString());
			}
			int constructorModifers = constructorObj[i].getModifiers();
			//printing out the modifiers of the constructor
			System.out.println("MODIFIER: " + Modifier.toString(constructorObj[i].getModifiers()));
			System.out.println("MODIFIERS: " + constructorModifers);
		}	
	}

	//inspects field object and prints out accordingly
	public static void fieldInspector(Class classObject, Object anObject) throws Exception {

		//setting meta FIELD object objectField
		Field [] fieldObj =  classObject.getDeclaredFields();

		//Field Handlers
		System.out.println("***** FIELD *****");

		for(int i = 0; i < fieldObj.length; i++) {
			fieldObj[i].setAccessible(true);

			checkField(fieldObj[i], anObject);

			//printing out type of the field 
			Class typeObject = fieldObj[i].getType();
			System.out.println("TYPE: " + typeObject.toString());
			//printing out the modifiers of the field
			int fieldModifiers = fieldObj[i].getModifiers();
			System.out.println("MODIFIER: " + Modifier.toString(fieldObj[i].getModifiers()));
			System.out.println("MODIFIERS: " + fieldModifiers);
		}
	}

	//checks a field for possible arrays, primitive and non-primitives and prints them out 
	public static Field checkField(Field fieldObj, Object anObject) throws Exception {
		if(fieldObj.getType().isPrimitive())
			//printing out FIELD NAME of the field
			System.out.println("FIELD NAME: " + fieldObj.getName());
		else
		{
			if (fieldObj.getType().isArray())
			{
				System.out.println("ARRAY FOUND!");

				System.out.println("ARRAY LENGTH:" + Array.getLength(fieldObj.get(anObject)));

				for (int k = 0; k < Array.getLength(fieldObj.get(anObject)); k++) {
					System.out.println("Index " + k + ":" + Array.get(fieldObj.get(anObject), k)); // another possible recursion
					//currentObject = Array.get(fieldObj.get(anObject), k);
					//checkField(fieldObj, Array.get(fieldObj.get(anObject), k));
				}
			}		
			else {
				System.out.println("non-primitive object, value: " + fieldObj.get(anObject)); // recursion occurs here

			}

		}		
		return null;
	}
	//traverses the superclass(es) of a class and prints out accordingly
	private static void traverseSuperClasses(Class cls) throws Exception
	{
		System.out.println("Traversing superclass(es) for " + cls.getName());

		while ( cls != null)
		{
			Class [] interfaces = cls.getInterfaces();
			System.out.println(String.format("%s implements %s", cls.getSimpleName(), Arrays.toString(interfaces)));
			int i = 0;
			for(i = 0; i < interfaces.length; i++) {
				//traverseSuperClasses(interfaces[i].getClass());
			}
			cls = cls.getSuperclass();

		} 
	}
}
