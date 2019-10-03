int pin =  7; //atribui o pino 
int dado; //vari�vel que receber� os dados da porta serial

int analogPin = A1;
int val = 0; 

void setup(){
  Serial.begin(9600);//frequ�ncia da porta serial
   pinMode(pin,OUTPUT); //define o pino como sa�da
}
 
void loop(){
  val = analogRead(analogPin);  // l� o pino de entrada
  Serial.println(val);    
  
  if(Serial.available() > 0){ //verifica se existe comunica��o com a porta serial
      dado = Serial.read();//l� os dados da porta serial
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