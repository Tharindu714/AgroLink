import React from 'react';
import { NavigationContainer } from '@react-navigation/native';
import { createStackNavigator } from '@react-navigation/stack';
import HomeScreen from './screens/HomeScreen';
import LiveDataScreen from './screens/LiveDataScreen';

const Stack = createStackNavigator();

export default function App() {
  return (
    <NavigationContainer>
      <Stack.Navigator
        initialRouteName="AgriLink Mobile App"
        screenOptions={{
          headerStyle: {
            backgroundColor: 'rgba(0, 0, 0, 0)', // Fully transparent
            elevation: 0, // No shadow on Android
            shadowOpacity: 0, // No shadow on iOS
          },
          headerTintColor: 'white', // Text color
          headerTransparent: true, // Makes the header itself transparent
          headerTitleStyle: {
            color: 'white', // Ensures the title text is white
          },
        }}
      >
        <Stack.Screen name="AgriLink Mobile App" component={HomeScreen}/>
        <Stack.Screen name="Monitor Live Data" component={LiveDataScreen} />
      </Stack.Navigator>
    </NavigationContainer>
  );
}

