// API_Project by Raman Srivastava.

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.*;

import com.google.gson.*;
import com.google.gson.reflect.*;
import org.jibble.pircbot.*;

public class API_Project {

	public static void main(String[] args) throws Exception {

		// Creates a new Chatbot.
		ChatBot apiBot = new ChatBot();
		
		// Sets the name to change automatically if the same name is found on the server, 
		// allows log to be shown in the console, connects the chatbot to the freenode website
		// to the #irchacks channel.
		apiBot.setAutoNickChange(true);
		apiBot.setVerbose(true);
		apiBot.connect("irc.freenode.net", 6667);
		apiBot.joinChannel("#irchacks");		
		
		// Allows the user to quit the chatbot and program in the console.
		Scanner checkQuit = new Scanner(System.in);
		String userQuit = checkQuit.next();
		
		if (userQuit.equals("quit"))
		{
			apiBot.dispose();
			System.exit(0);
		}
		
		checkQuit.close();
		
		// Test to check if the Weather API worked in console.
		/*
		String API_KEY = "c145694aab4cdbb166950070f901b55e"; 
		String userLocation = "";
		boolean checkString = false;
		Scanner userInput = new Scanner(System.in);
		
		System.out.print("Enter the city or ZIP code for the weather: ");
	
		userLocation = userInput.next();
	
		while (checkString == false)
		{		
			String weatherURL = "http://api.openweathermap.org/data/2.5/weather?q=" 
					+ userLocation + ",US&appid=" + API_KEY + "&units=imperial";
			
		
			System.out.print(getWeather(weatherURL));
			checkString = true;
			
			
			if (checkString == false)
			{
				System.out.print("Error, please try again (Ex. 75080 or Dallas): ");
				userLocation = userInput.next();
			}
		
		}	
		userInput.close();
		*/
		
		// Test to check if the Email API worked in console.
		/*
		String API_KEY = "dc57e19f41d9605349aa4c08af8faf3c";
		
		String userEmail = "";
		
		String emailURL = "https://apilayer.net/api/check?access_key=" + API_KEY;
		
		Scanner userInput = new Scanner(System.in);
		
		System.out.print("Type in your email: ");
		userEmail = userInput.next();
		
		emailURL = emailURL + "&email=" + userEmail.trim();
		
		userInput.close();	
		
		
		String result = getEmailValidation(emailURL);
		
		System.out.println(result);
		*/
		
		// Test to check if the Phone Number API worked in console.
		/*
		String API_KEY = "479794a592631e3c92e54c2c20906119";
		String userNumber= "";
		String phoneURL = "http://apilayer.net/api/validate?access_key=" + API_KEY;
		

		Scanner userInput = new Scanner(System.in);
		
		System.out.print("Type in your number: ");
		userNumber = userInput.next();
		
		phoneURL = phoneURL + "&number=1" + userNumber.trim();
		
		userInput.close();	
		
		
		String result = getPhoneValidation(phoneURL);
	
		System.out.println(result);
		*/


	}
	
	
	// Interacts with the Weather API to provide the weather.
	public static String getWeather(String weatherURL)
	{
		
		try 
		{
			
			// Connects to the API via the JSON's website URL.
			StringBuilder result = new StringBuilder();
			URL locationURL = new URL(weatherURL);
			URLConnection testConnection = locationURL.openConnection();
			BufferedReader userReader = new BufferedReader(new InputStreamReader(testConnection.getInputStream()));
			
			String weatherLine;
			
			// Stores the JSON as a Java string.
			while ((weatherLine = userReader.readLine()) != null)
			{
				result .append(weatherLine);
			}
			
			userReader.close();
			
			// Parses the JSON to the specific directories. 
			Map<String, Object> resMap = jsonToMap(result.toString());
			Map<String, Object> mainMap = jsonToMap(resMap.get("main").toString());
			Map<String, Object> windMap = jsonToMap(resMap.get("wind").toString());
			
			String resultString;
			
			// Returns the result by accessing the sub-directories. 
			resultString = "The average temperature in " + resMap.get("name") 
					+ " is " + mainMap.get("temp") 
					+ "°F with a high of " + mainMap.get("temp_max")
					+ "°F and a low of " + mainMap.get("temp_min")
					
					+ "°F. The wind speed is " + windMap.get("speed") + " mph.";
					

			return resultString;
		}

		// Catches the exception.
		catch (IOException e)
		{
			return "Error, could not identify user input.";
		}
	}
	
	// Interacts with the Email API to provide the Email Validation.
	public static String getEmailValidation(String emailURL)
	{
		
		try 
		{
			// Connects to the API via the JSON's website URL.
			StringBuilder result = new StringBuilder();
			URL locationURL = new URL(emailURL);
			URLConnection testConnection = locationURL.openConnection();
			BufferedReader userReader = new BufferedReader(new InputStreamReader(testConnection.getInputStream()));
			
			String emailLine;
			
			String resultString;
			String emailCheck;
			
			// Stores the JSON as a Java string.
			while ((emailLine = userReader.readLine()) != null)
			{
				result .append(emailLine);
			}
			
			result.toString();
			
			userReader.close();
			
			// Returns the result by accessing the subdirectories. 
			Map<String, Object> resMap = jsonToMap(result.toString());
		
			if (resMap.get("smtp_check").toString().equals("true"))
			{
				emailCheck = "a valid email address.";
			}
			
			else 
			{
				emailCheck = "not a valid email address.";
			}
			
			resultString = "The email '" + resMap.get("email") + "' is " + emailCheck;
			
			
			return resultString;
		}

		// Catches the exception.
		catch (IOException e)
		{
			return "Error, could not identify user input.";
		}
	}
	
	// Interacts with the Phone API to provide the Phone Number Validation.
	public static String getPhoneValidation(String phoneURL)
	{
		
		try 
		{
			
			// Connects to the API via the JSON's website URL.
			StringBuilder result = new StringBuilder();
			URL locationURL = new URL(phoneURL);
			URLConnection testConnection = locationURL.openConnection();
			BufferedReader userReader = new BufferedReader(new InputStreamReader(testConnection.getInputStream()));
			
			String phoneLine;
			
			String resultString;
			String numberCheck;
			
			// Stores the JSON as a Java string.
			while ((phoneLine = userReader.readLine()) != null)
			{
				result .append(phoneLine);
			}
			
			result.toString();
			
			userReader.close();
			
			// Returns the result by accessing the subdirectories. 
			Map<String, Object> resMap = jsonToMap(result.toString());
			
			
			if (resMap.get("valid").toString().equals("true"))
			{
				numberCheck = "a valid phone number.";
			}
			
			else 
			{
				numberCheck = "not a valid phone number.";
			}
		
			// Catches the exception.
			resultString = "The phone number '" + resMap.get("number") + "' is " + numberCheck;
		
			
			return resultString;
		}

		catch (IOException e)
		{
			return "Error, could not identify user input.";
		}
	}
	
	// Function that parses the JSON's directories.
	public static Map<String, Object> jsonToMap(String str)
	{
		Map<String, Object> map = 
				new Gson().fromJson(str, new TypeToken<HashMap<String, Object>>() {}.getType());
		
		return map;
	}

}


// Chatbot class with user command functions.
class ChatBot extends PircBot
{
	public ChatBot()
	{
		// Name of the Chatbot.
		this.setName("ApiBot");
	}
	
	// Outputs the API results when the user types in the commands.
	public void onMessage(String channel, String sender, String login, String hostname, String message) 
	{
		// Basic command for time.
		if (message.equalsIgnoreCase("time")) 
		{
			String time = new java.util.Date().toString();
			sendMessage(channel, sender + ": The time is now " + time);
		}
		
		// Displays basic weather information in the US, city or zip code.
		else if (message.toLowerCase().startsWith("weather "))
		{
			String weatherURL = "http://api.openweathermap.org/data/2.5/weather?q=" 
					+ message.substring(8).trim() + ",US&appid=" + "c145694aab4cdbb166950070f901b55e" + "&units=imperial";
			
			sendMessage(channel, sender + "" + API_Project.getWeather(weatherURL));
		}
		
		// Checks if the user input for an email is valid, using SMTP.
		else if (message.toLowerCase().startsWith("check email "))
		{
			String emailURL = "https://apilayer.net/api/check?access_key=dc57e19f41d9605349aa4c08af8faf3c"
					+ "&email=" + message.substring(12).trim();
			
			sendMessage(channel, sender + "" + API_Project.getEmailValidation(emailURL));
		}
		
		// Checks if the user input for a phone number is valid.
		else if (message.toLowerCase().startsWith("check number "))
		{
			String phoneURL = "http://apilayer.net/api/validate?access_key=479794a592631e3c92e54c2c20906119"
					+ "&number=" + message.substring(13).trim();
			
			sendMessage(channel, sender + "" + API_Project.getPhoneValidation(phoneURL));
		}
		
		// Displays the basic user command if the user inputs help.
		else if (message.toLowerCase().startsWith("help"))
		{
			sendMessage(channel, sender + "     ApiBot Commands:");
			sendMessage(channel, sender + "     Time: Outputs the current time.");
			sendMessage(channel, sender + "     Weather XXXXX: Outputs the current weather in a given city or zip code.");
			sendMessage(channel, sender + "     Check Email XXXXX: Checks if an email is valid or not." );
			sendMessage(channel, sender + "     Check US Number XXXXX: Checks if a phone number is valid or not (add the area code).");
		
		}
		
		// Hidden user command for quitting the chatbot (for convenience testing purposes).
		else if (message.toLowerCase().startsWith("quit"))
		{
			quitServer();
			this.dispose();
			System.exit(0);
		}
	}
	
}
