import java.util.Arrays;
import java.util.Scanner;

public class make_all_0 {
	/* JUEGO CREADO POR:
 	* @author Alvaro Velasco @Velastroll
 	*
 	* 1 ING.INF. @ UVa
 	*/

	public static void main(String[] args) {

    	int f=6, c=6, max = f*10+c, play = 0; // Cambiando el valor de F o C tendremos un juego diferente
    	int[][] matriz = new int [f] [c];
    	int[][] matrizfinal = new int [f] [c];
 
    	int nivel = 5; // Predeterminadamente se empieza en el nivel 5
    	int golpes = 0;

    	Scanner teclado;
    	teclado = new Scanner (System.in);

    	//Se genera un tablero aleatorio

    	matriz = genMnivel(nivel, f, c);
    	info (matriz, golpes, nivel);

    	matrizfinal = genMde0(f,c); 	// matriz de 0 con la que se comparala usada en el juego para saber si se ha resuelto
    	int[][] mA = copyArray(matriz); //mA es la copia de la matriz inicial que se usará por si se quiere recomenzar a jugar

    	/* COMANDOS DEL MENÚ */
    	
    	while (play != -2) {							// (-2) Cuando 'play' sea -2 se sale del juego.

        	play = teclado.nextInt(); 					// variable utilizada para comandos y para jugar

        	if (play==1){ 								// (1) Recomenzamos el tablero, guardado en mA
            	matriz=copyArray(mA);
            	golpes = 0;
            	info (matriz, golpes, nivel);
        	}
        	else {
            	if (play == 2){							// (2) Obtenemos un tablero nuevo del mismo nivel
                	matriz = genMnivel (nivel,f,c);
                	info (matriz, golpes, nivel);

                	}
            	else {					
                	if (play==3){/* calificacion */	}	// (3) Datos sobre la clasificación
                	
                	else { if (play==4) {				// (4) Cambiar de nivel

                    	System.out.println("¿A qué nivel del 1 al 9 desea cambiar? (Escriba 0 para seguir igual)");
                		int a = teclado.nextInt();
                    	if (a==0){

                        	System.out.println("# CONTINÚA COMO ANTES #");
                        	System.out.println("");
                        	info (matriz, golpes,nivel);
                    	} else {
                    		
                    		nivel = a;
                        	while (a<1 || 9<a) { 		// Por si el numero introducido no es válido
                            	System.out.println("Nivel no válido, pruebe de nuevo introduciendo un número entero del 1 al 9");
                            	nivel = teclado.nextInt();
                            	golpes=0;
                            	}
                        	matriz = genMnivel(nivel, f, c);
                        	System.out.println("# HA CAMBIADO AL NIVEL " + nivel + " #");
                        	System.out.println("");
                        	info (matriz, golpes,nivel);

                        	mA = copyArray(matriz); 			// al cambiar de nivel, volvemos a actualizar mA


                    	
                    		}

                    	}
                    	else {									// Para el resto de numero que puede ser 'play'
                        	if (play<max+1 & 10<play & play%10<=c) {
                        		
                            	golpes += 1;
                            	matriz = juego(play, matriz);
                            	info (matriz, golpes,nivel);
                            	
                            	if (Arrays.deepEquals(matrizfinal, matriz)){		 // Se ejecuta cuando ganas el juego
                                	                          		
                                	infojuegoganado (nivel, golpes);
                                	play = teclado.nextInt();
                                	
                                	while (play !=1 && play !=-1) {

                                    	System.out.print("Introduzca bien el número: ");
                                    	play = teclado.nextInt();
                                    	}


                                 	if (play==1) {
                                 			golpes=0;
                                 			nivel+=1;
                                 			matriz = genMnivel(nivel, f, c);
                                         	info (matriz, golpes, nivel);
                                 	}

                                 	if (play==-1){
                                	play=-2;
                                 	}
                            	}
                        	}

                        	else {
                        		if (play!=-2){
                            	System.out.println("Número no válido, pruebe de nuevo.");
                        		}
                        	}
                    	}
                	}
            	}
        	}
    	}
    	System.out.println("¡VUELVA PRONTO!");
	}


	public static int[][] genMde0 (int f, int c){    	// Genera una matriz entera de 0 dependiendo de las filas y columnas que le des
   	  int[][] matriz = new int [f] [c];
     	for (int fila =0 ; fila<matriz.length ; fila++) {
                 	for (int colu=0 ; colu<matriz[0].length ; colu++) {
                             	matriz [fila] [colu] = 0;
                 	}
     	}
     	return matriz;
 	}

 	public static int[][] genMnivel (int nivel, int f, int c){ // Toma una matriz de 0 y genera la matriz dependiendo del nivel
     	int golpes=3*nivel;    	// Los golpes necesarios son 3 veces el nivel

     	int matriz[][]= genMde0(f,c);
     	for (int i = 0; i<golpes; i++) {

         	int a = (int) (matriz.length*Math.random());
         	int b = (int) (matriz[0].length*Math.random());

         	if (matriz[a][b] == 3) {matriz[a][b] = 0;}
         	else { matriz [a][b] += 1;}

         	if (a<(matriz.length-1)) {
             	if (matriz[a+1][b] == 3) {matriz[a+1][b] = 0;}
             	else { matriz [a+1][b] += 1;}
         	}
         	if (a>0) {
             	if (matriz[a-1][b] == 3) {matriz[a-1][b] = 0;}
             	else { matriz [a-1][b] += 1;}
         	}

         	if (b<(matriz[0].length-1) ) {
             	if (matriz[a][b+1] == 3) {matriz[a][b+1] = 0;}
             	else { matriz [a][b+1] += 1;}
         	}

         	if (b>0) {
             	if (matriz[a][b-1] == 3) {matriz[a][b-1] = 0;}
             	else { matriz [a][b-1] += 1;}
         	}
     	}


         	return matriz;
 	}

 	public static void imprimir (int[][]  matriz){	//	Esto imprime la matriz
     	for(int fila=0 ; fila<matriz.length ; fila++){
                 	for (int colu=0 ; colu<matriz[0].length ; colu++){
                     	System.out.print(matriz[fila][colu] + " ");
                 	}

             	System.out.println();
             	}

 	}

 	public static int[][] juego (int play, int [][] matriz){ // Proceso por el cual se resta según la fila y columna elegida
     	int f = (play/10)-1; // La matriz utiliza valores del 0-5 mientras que le damos del 1-6
     	int c = (play%10)-1;

     	if (matriz[f][c] == 0) {matriz[f][c] = 3;}
     	else { matriz [f][c] -= 1;}

     	if (f<(matriz.length-1)) {
         	if (matriz[f+1][c] == 0) {matriz[f+1][c] = 3;}
         	else { matriz [f+1][c] -= 1;}
     	}
     	if (f>0) {
         	if (matriz[f-1][c] == 0) {matriz[f-1][c] = 3;}
         	else { matriz [f-1][c] -= 1;}
     	}

     	if (c<(matriz[0].length-1) ) {
         	if (matriz[f][c+1] == 0) {matriz[f][c+1] = 3;}
         	else { matriz [f][c+1] -= 1;}
     	}

     	if (c>0) {
         	if (matriz[f][c-1] == 0) {matriz[f][c-1] = 3;}
         	else { matriz [f][c-1] -= 1;}
     	}

     	return matriz;
 	}

 	public static void info (int [][] matriz, int golpes, int nivel) {
     	System.out.println ("Recomenzar (01) - Nuevo (02) - Calificación (03) - Cambiar nivel (04) - Salir (0-2)");
     	System.out.println("Un golpe decrementará el valor de esa casilla en 1, y también los valores de sus 4 vecinas.");
     	System.out.println("Objetivo: Dejar todas las casillas en '0'");
     	System.out.println("AYUDA: la casilla de arriba a la izquierda es el 11, la de abajo a la derecha es la 66 (en matriz 6x6)");
     	System.out.println("");
     	System.out.println("Golpes: " + golpes + " 	Nivel: " + nivel);
     	
     	imprimir (matriz);
 	}

 	public static int [][] copyArray (int [][]matriz){

     	int [][] m2 = new int [matriz.length][matriz[0].length];

     	for(int fila=0 ; fila<matriz.length ; fila++){
   		  for (int colu=0 ; colu<matriz[0].length ; colu++){

             	m2[fila][colu]=matriz[fila][colu];
         	}

     	}

 	return m2;
 }

 	public static void infojuegoganado (int nivel, int golpes){
		
 		double resultado = (nivel*3/(double)golpes);
    	
 		if (resultado==1) System.out.println("¡PERFECTO! Hecho en " + golpes + " golpes");
    	if (resultado>1) System.out.println("¡EXTRAORDINARIAMENTE BIEN! Hecho en " + golpes + " golpes");
    	if (resultado<1) System.out.println("Hecho en " + golpes + " golpes");


    	System.out.println("Tu puntuación es: " + (resultado));
    	System.out.println("¿Desea seguir jugando? (1:Sí  -1:No)"); 		// Opciones cuando ganas el juego
 	}
}