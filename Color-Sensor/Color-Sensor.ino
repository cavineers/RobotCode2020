// Definitions
#define S0 2
#define S1 3
#define S2 4
#define S3 5
#define sensorOut 6
#define LED 7

// Vars
int DataR = 0;
int DataG = 0;
int DataB = 0;
int Counter = 0;
int frequency = 0;

// Current color
String CurrentColor = "Unknown";

// Setup
void setup() {
    // Config pins
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
    // ==RED==

    // Config the red filter
    digitalWrite(S2,LOW);
    digitalWrite(S3,LOW);

    // read the red filter
    frequency = pulseIn(sensorOut, LOW);
    DataR = frequency;
    
    // Wait
    delay(1);

    // ==GREEN==

    // Config the green filter
    digitalWrite(S2,HIGH);
    digitalWrite(S3,HIGH);

    // read the green filter
    frequency = pulseIn(sensorOut, LOW);
    DataG = frequency;

    // Wait
    delay(1);

    // ==BLUE==

    // config the blue filter
    digitalWrite(S2,LOW);
    digitalWrite(S3,HIGH);
  
    // read the blue filter
    frequency = pulseIn(sensorOut, LOW);
    DataB = frequency;

    // Wait
    delay(1);

    // ==PRINT==
    //^ only for debug
    // Serial.println("R=" + String(DataR) + "    G=" + String(DataG) + "    B=" + String(DataB));

    // ==COLOR MATCH==
    if((DataR>=60 && DataR<=80) && (DataG>= 100 && DataG <= 125) && (DataB>=65 && DataB<=85) && Counter <= 3 ){
        CurrentColor = "Red";
        Counter = 0;
        Serial.println("R");
    }
    else if ((DataR>=90 && DataR<=110) && (DataG>=75 && DataG<=90) && (DataB>=40 && DataB<=60) && Counter <= 3) {
        CurrentColor ="Blue";
        Counter = 0;
        Serial.println("B");
    }
    else if ((DataR>=90 && DataR<=120) && (DataG>=90 && DataG<=100) && (DataB>=65 && DataB<=75) && Counter <= 3){
        CurrentColor = "Green";
        Counter = 0;
        Serial.println("G");
    }
    else if ((DataR>=55 && DataR<=65) && (DataG>=65 && DataG<=75) && (DataB>=60 && DataB<=73) && Counter <= 3){
        CurrentColor = "Yellow";
        Counter = 0;
        Serial.println("Y");
    }
    else if (Counter >= 3) {
        Serial.println("N");
        Counter = 0; 
    }
    else{
        if(CurrentColor.equals("Unknown")){
            Serial.println("U");
        }
        else{
            Counter++;
        }
    }

    // Wait
    delay(1);
}
