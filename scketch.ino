int pin =  7; //atribui o pino 
int dado; //variável que receberá os dados da porta serial

int analogPin = A1;
int val = 0; 

void setup(){
  Serial.begin(9600);//frequência da porta serial
   pinMode(pin,OUTPUT); //define o pino como saída
}
 
void loop(){
  val = analogRead(analogPin);  // lê o pino de entrada
  Serial.println(val);    
  
  if(Serial.available() > 0){ //verifica se existe comunicação com a porta serial
      dado = Serial.read();//lê os dados da porta serial
      switch(dado){
        case 1:
           digitalWrite(pin, HIGH); //liga o pino
        break;
        case 2:
           digitalWrite(pin, LOW); //desliga o pino
         break;
    }
  }
}