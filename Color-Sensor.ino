#define S0 2
#define S1 3
#define S2 4
#define S3 5
#define sensorOut 6
#define LED 7
int DataR = 0;
int DataG = 0;
int DataB = 0;
int Counter = 0;
int frequency = 0;
String CurrentColor = "Unknown";
void setup() {
  pinMode(S0, OUTPUT);
  pinMode(S1, OUTPUT);
  pinMode(S2, OUTPUT);
  pinMode(S3, OUTPUT);
  pinMode(sensorOut, INPUT);
  pinMode(LED, OUTPUT);
  // Setting frequency-scaling to 20%
  digitalWrite(S0,HIGH);
  digitalWrite(S1,LOW);
  digitalWrite(LED, HIGH);
  Serial.begin(9600);
}
void loop() {
  // Setting red filtered photodiodes to be read
  digitalWrite(S2,LOW);
  digitalWrite(S3,LOW);
  // Reading the output frequency
  frequency = pulseIn(sensorOut, LOW);
  DataR = frequency;
  // Printing the value on the serial monitor
//  Serial.print("R= ");//printing name
//  Serial.print(frequency);//printing RED color frequency
//  Serial.print("  ");
  delay(1);
  // Setting Green filtered photodiodes to be read
  digitalWrite(S2,HIGH);
  digitalWrite(S3,HIGH);
  // Reading the output frequency
  frequency = pulseIn(sensorOut, LOW);
  DataG = frequency;
  // Printing the value on the serial monitor
//  Serial.print("G= ");//printing name
//  Serial.print(frequency);//printing RED color frequency
//  Serial.print("  ");
  delay(1);
  // Setting Blue filtered photodiodes to be read
  digitalWrite(S2,LOW);
  digitalWrite(S3,HIGH);
  // Reading the output frequency
  frequency = pulseIn(sensorOut, LOW);
  DataB = frequency;
  // Printing the value on the serial monitor
//  Serial.print("B= ");//printing name
//  Serial.print(frequency);//printing RED color frequency
//  Serial.println("  ");
  delay(1);
  if((DataR>=60 && DataR<=80) && (DataG>= 100 && DataG <= 125) && (DataB>=65 && DataB<=85) && Counter <= 3 ){
    CurrentColor = "Red";
//    Serial.println("The current color is " + CurrentColor);
    Counter = 0;
    Serial.println("R");
  }
  else if ((DataR>=90 && DataR<=110) && (DataG>=75 && DataG<=90) && (DataB>=40 && DataB<=60) && Counter <= 3) {
    CurrentColor ="Blue";
//    Serial.println("The current color is " + CurrentColor);
    Counter = 0;
    Serial.println("B");
  }
  else if ((DataR>=90 && DataR<=120) && (DataG>=90 && DataG<=100) && (DataB>=65 && DataB<=75) && Counter <= 3){
    CurrentColor = "Green";
//    Serial.println("The current color is " + CurrentColor);
    Counter = 0;
    Serial.println("G");
  }
  else if ((DataR>=55 && DataR<=65) && (DataG>=65 && DataG<=75) && (DataB>=60 && DataB<=73) && Counter <= 3){
    CurrentColor = "Yellow";
//    Serial.println("The current color is " + CurrentColor);
    Counter = 0;
    Serial.println("Y");
  }
  else if (Counter >= 3) {
    Serial.println("N");
  //Serial.println("We Broke it");
  Counter = 0; 
  }
  else{
//    Serial.println("Previous known color is " + CurrentColor);
      if(CurrentColor.equals("Unknown")){
        Serial.println("U");
      }
      else{
        Counter++;
      }
//    Serial.println("Previous: " + CurrentColor);
  }
  delay(1);
}
