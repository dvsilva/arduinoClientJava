int pin =  7; //atribui o pino 
int dado; //variável que receberá os dados da porta serial
 
void setup(){
  Serial.begin(9600);//frequência da porta serial
   pinMode(pin,OUTPUT); //define o pino o ledPin como saída
}
 
void loop(){
  if(Serial.available() > 0){ //verifica se existe comunicação com a porta serial
      dado = Serial.read();//lê os dados da porta serial
      switch(dado){
        case 1:
           digitalWrite(pin, HIGH); //liga o pino ledPin
        break;
        case 2:
           digitalWrite(pin, LOW); //desliga o pino ledPin
         break;
    }
  }
}
