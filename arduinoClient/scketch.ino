int pin =  7; //atribui o pino 
int dado; //vari�vel que receber� os dados da porta serial
 
void setup(){
  Serial.begin(9600);//frequ�ncia da porta serial
   pinMode(pin,OUTPUT); //define o pino o ledPin como sa�da
}
 
void loop(){
  if(Serial.available() > 0){ //verifica se existe comunica��o com a porta serial
      dado = Serial.read();//l� os dados da porta serial
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
