package com.imacho.fulltexturebackgroundanimation;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;
//import java.util.Random;

import javax.microedition.khronos.opengles.GL10;


import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.opengl.GLES20;
import android.opengl.GLUtils;
import android.util.Log;

public class PrimitivesObject {
	
	//MyComplex mycomplex_k;
	
	//MyComplex c = new MyComplex(0.109, 0.603);
	int width = 100, height = 100;
	//private int its_loop =0;
	//private float r_julia_m =0.f;
	boolean doJuliaSet = true;
	int idx_vertex = 0;
	int idx_vertex_color = 0;
	
	private float verticesbackground[] = {
			0.0f, 0.0f,  0.0f,		// V1 - bottom left
			0.0f,  1.0f,  0.0f,		// V2 - top left
			 1.0f, 0.0f,  0.0f,		// V3 - bottom right
			 1.0f,  1.0f,  0.0f			// V4 - top right
	};

	//private FloatBuffer textureBuffer;	// buffer holding the texture coordinates
	private float texturebackground[] = {    		
			// Mapping coordinates for the vertices
			0.0f, 1.0f, 0.0f,		// top left		(V2)
			0.0f, 0.0f, 0.0f,		// bottom left	(V1)
			1.0f, 1.0f,	0.0f,	// top right	(V4)
			1.0f, 0.0f, 0.0f		// bottom right	(V3)
	};
	

	private int[] imageFileIDs = { // Image file IDs
			R.drawable.nature, R.drawable.mule, R.drawable.joystick, R.drawable.joystick_bg,
			R.drawable.meow,R.drawable.earth,R.drawable.ground,R.drawable.village,R.drawable.neon2};
	
	private int[] imageFileIDs2 = { // Image file IDs
			R.drawable.bird1, R.drawable.bird2, R.drawable.bird3, R.drawable.bird4,
			R.drawable.bird5, R.drawable.bird6, R.drawable.bird7, R.drawable.bird8};
	
	private int[] buttonId = {
			R.drawable.about,
			R.drawable.new_game,
			R.drawable.high_score,
			R.drawable.exit_game
	};
	
	private float vertices[] = { -0.5f, -0.5f, 0.0f, // V1 - first vertex
														// (x,y,z)
			-0.5f, 0.5f, 0.0f, // V2
			0.5f, 0.5f, 0.0f, // V3
			0.5f, -0.5f, 0.0f, // V4
			-0.5f, -0.5f, 0.0f // V5
	};
	
	private float textCoord_Triagle[]={	
			1.0f, 1.0f,  0.0f,		// V3
			0.0f, 1.0f,  0.0f,		// V2
			0.5f, 0.0f,  0.0f,	// V1 - first vertex (x,y,z)		
	};

	int[] textures_indek = new int[1];
	
	private float[] vertices_quad = { // Vertices for the square
	-1.0f, -1.0f, 0.0f, // 0. left-bottom
			1.0f, -1.0f, 0.0f, // 1. right-bottom
			-1.0f, 1.0f, 0.0f, // 2. left-top
			1.0f, 1.0f, 0.0f // 3. right-top
	};

	private float vertices_color[] = { 1.0f, 0.0f, 0.0f, 1.0f, // CV1 - first
																// color
																// (red,green,blue)
			0.0f, 1.0f, 0.0f, 1.0f, // CV2
			0.0f, 0.0f, 1.0f, 1.0f, // CV3
			0.0f, 1.0f, 0.0f, 1.0f, // CV4
			1.0f, 0.0f, 0.0f, 1.0f // CV5
	};

	//private float vertices_horizontal[] = { 0.0f, 0.0f, 0.0f };
	private float vertices_horiverti[] = { 0.0f, 0.0f, 0.0f };
	private float vertices_labirin_player[] = { 0.0f, 0.0f, 0.0f };
	private float vertices_circle[] = { 0.0f, 0.0f, 0.0f };
	private float vertices_circle_color[] = { 0.0f, 0.0f, 0.0f, 0.5f };
	private float vertices_line[] = { 0.0f, 0.0f, 0.0f };
	private float vertices_line_color[] = { 0.0f, 0.0f, 0.0f, 1.0f };
	private float vertices_fractal_mandelbrot[]={0.0f, 0.0f, 0.0f};
	private float vertices_color_fractal_mandelbrot[]={0.0f, 0.0f, 0.0f, 0.0f};
	
	private float vertices_fractal_julia[]={0.0f, 0.0f, 0.0f};
	private float vertices_color_fractal_julia[]={0.0f, 0.0f, 0.0f, 0.0f};
	
	private float vertices_fractal_julia_m[]={0.0f, 0.0f, 0.0f};
	private float vertices_color_fractal_julia_m[]={0.0f, 0.0f, 0.0f, 0.0f};
	
	
	
	private int batas_sudut = 360;
	float jari_jari;
	float a, b;
	float x, y;
	float step = 3.0f, step_line = 0.2f;
	float x1, y1;
	float x2, y2;
	private int loop, loop_color, loop_line, loop_line_color;
	
	float WidthObject=0.08f;
	int numberRow=10,numberCol=10; 
	int positionRow;
	int positionCol;
	int positionRow_end;
	int positionCol_end;
	int Navigate;
	int draw=1; 
	float x_player=0; // posisi start
	float y_player=0;
	float x_player_end=0; // posisi end
	float y_player_end=0;
	
	// menyimpan inisialisasi array untuk labirin 
	int hori[][] = new int[2*numberRow*numberCol][2*numberRow*numberCol];
	int verti[][] = new int[2*numberRow*numberCol][2*numberRow*numberCol];
	private int maze[][] = new int[2*numberRow*numberCol][2*numberRow*numberCol];

	public PrimitivesObject() {
		
		//this.mycomplex = new MyComplex(WidthObject, WidthObject);

		// ============ start to generate vertices to circle
		// ==========================
		// Inisialisasi
		jari_jari = 1.0f;

		// Titik Pusat
		a = 0.0f;
		b = 0.0f;
		x = a + jari_jari;
		y = b;

		loop = 3;
		loop_color = 4;
		vertices_circle = new float[(int) (3 * batas_sudut / step) * 3];
		vertices_circle_color = new float[(int) (3 * batas_sudut / step) * 4];
		for (float teta = 0; teta <= 2 * batas_sudut; teta += step) {
			vertices_circle[loop] = (float) ((x - a)
					* Math.cos((teta / 180) * (22 / 7))
					- ((y - b) * Math.sin((teta / 180) * (22 / 7))) + a);
			vertices_circle[loop + 1] = (float) ((x - a)
					* Math.sin((teta / 180) * (22 / 7))
					- ((y - b) * Math.cos((teta / 180) * (22 / 7))) + b);
			vertices_circle[loop + 2] = 0;
			loop += 3;

			// mengenerate warna untuk setiap vertex
			vertices_circle_color[loop_color] = (float) ((x - a)
					* Math.cos((teta / 180) * (22 / 7))
					- ((y - b) * Math.sin((teta / 180) * (22 / 7))) + a);
			vertices_circle_color[loop_color + 1] = (float) ((x - a)
					* Math.sin((teta / 180) * (22 / 7))
					- ((y - b) * Math.cos((teta / 180) * (22 / 7))) + b);
			vertices_circle_color[loop_color + 2] = 0.5f;
			vertices_circle_color[loop_color + 3] = 0.5f;
			loop_color += 4;
		}
		// ============= end for generate vertices to circle
		// ====================

		// ============ start to generate vertices to line
		// ==========================
		x1 = -1.0f;
		y1 = -1.0f;
		x2 = 1.0f;
		y2 = 1.0f;

		loop_line = 3;
		loop_line_color = 4;
		vertices_line = new float[(int) (2 * (x2 - x1) / step_line) * 3];
		vertices_line_color = new float[(int) (2 * (x2 - x1) / step_line) * 4];

		float m = (y2 - y1) / (x2 - x1);
		for (x = x1; x <= x2; x += step_line) {
			vertices_line[loop_line] = (float) (x);
			vertices_line[loop_line + 1] = (float) (m * (x - x1) + y1);
			vertices_line[loop_line + 2] = 0;
			loop_line += 3;

			// mengenerate warna untuk setiap vertex
			vertices_line_color[loop_line_color] = (float) (0.5 * x);
			vertices_line_color[loop_line_color + 1] = (float) (0.5 * m
					* (x - x1) + y1);
			vertices_line_color[loop_line_color + 2] = 1.0f;
			vertices_line_color[loop_line_color + 3] = 1.0f;
			loop_line_color += 4;
		}
		// ============= end for generate vertices to line ====================
		
		
		int i, j;
		vertices_horiverti = new float[2*numberRow*numberCol*6];
		vertices_labirin_player = new float[2*3*3];
		// start generate jaring-jaring labirin	
		//////////////////////////////////////////
		generateMaze();
		
		//Log.v("ii Test get numberRow : ", "" + numberRow);
	    //Log.v("ii Test get numberCol : ", "" + numberCol);
		
		  //line horizontal		  
		   int loop_vertices_horiverti=0;
		   float y = (WidthObject * numberRow);
		   for(i = 0; i < numberRow+1; i++)
		   {
		        for(j = 0; j < numberCol; j++){
		             if (hori[i][j] == 1)
		             {	                   
		                   // menampung vertex yang terbentuk
		                   vertices_horiverti[loop_vertices_horiverti]=j*WidthObject;
		                   vertices_horiverti[loop_vertices_horiverti+1]=y;
		                   vertices_horiverti[loop_vertices_horiverti+2]=0;
		                   
		                   vertices_horiverti[loop_vertices_horiverti+3]=j*WidthObject + WidthObject;
		                   vertices_horiverti[loop_vertices_horiverti+4]=y;
		                   vertices_horiverti[loop_vertices_horiverti+5]=0;
		                   loop_vertices_horiverti+=6;    
		             }
		        }
		        y -= WidthObject;		        
		   }
		   
		 //line vertical
	     float x = 0;
	     for(j = 0; j < numberCol+1; j++)
	     {
	          for(i = 0; i < numberRow; i++){
	               if (verti[i][j] == 1){
	            	     // menampung vertex yang terbentuk
	                     vertices_horiverti[loop_vertices_horiverti]=x;
	                     vertices_horiverti[loop_vertices_horiverti+1]=(numberRow-i)*WidthObject;
	                     vertices_horiverti[loop_vertices_horiverti+2]=0;
	                   
	                  	 vertices_horiverti[loop_vertices_horiverti+3]=x;
	                  	 vertices_horiverti[loop_vertices_horiverti+4]=(numberRow-i)*WidthObject-WidthObject;
	                  	 vertices_horiverti[loop_vertices_horiverti+5]=0;
	                  	 loop_vertices_horiverti+=6; 
	               }
	          }
	          x += WidthObject;	          
	     }
	     
	   //triagle    
	     vertices_labirin_player[0]=(positionCol * WidthObject) + (WidthObject/2);
	     vertices_labirin_player[1]=(numberRow - positionRow - 1)*WidthObject + (WidthObject * 3 / 4);
	     vertices_labirin_player[2]=0;
	     
	     vertices_labirin_player[3]=(positionCol * WidthObject) + (WidthObject/4);	     
	     vertices_labirin_player[4]=(numberRow - positionRow - 1)*WidthObject + (WidthObject / 4);
	     vertices_labirin_player[5]=0;
	     
	     vertices_labirin_player[6]=(positionCol * WidthObject) + (WidthObject*3/4);	     
	     vertices_labirin_player[7]=(numberRow - positionRow - 1)*WidthObject + (WidthObject / 4);
	     vertices_labirin_player[8]=0;

	     x = (positionCol * WidthObject) + (WidthObject/2);
	     y = (numberRow - positionRow - 1)*WidthObject + (WidthObject / 2);
	     this.x_player=x;
	     this.y_player=y;
	     
	     this.x_player_end=(positionCol_end * WidthObject) - (WidthObject/2);;
	     this.y_player_end=(numberRow- positionRow_end - 1)*WidthObject + (WidthObject / 2);;
	     
	     
	     Log.v("Test get x_player : ", "" + x);
		 Log.v("Test get y_player : ", "" + y);
		 
		 Log.v("Test get x_player_end : ", "" + x_player_end);
		 Log.v("Test get y_player_end : ", "" + y_player_end);
		 
		 
		// ============ start to generate vertices to fractal mandelbrot
		/*    int k;
			float x0,y0,xn,yn,xn1,yn1,c1,c2,thrd;
			x0=-2f; y0=-2f;
			c1=-2f; c2=-2f;
			
			// start counter length array
			int up_bound_1=479;
			int up_bound_2=639;
			int up_bound_3=1024;
			up_bound_1=479;
			up_bound_2=639;
			up_bound_3=1024;
			int length_fractal_mandelbrot=0;
			int step_=9;
			for(i=0;i<up_bound_1;i+= step_){
				for(j=0;j<up_bound_2;j+= step_){
					xn = x0 + ( j * 0.00625f );  // moving from pixel to pixel horizontally..
					yn = y0 + ( i * 0.00833f ); // moving from pixel to pixel vertically..
				
					c1=-2+(j*0.00625f);   // incrementing the value of C1
					c2=-2+(i*0.00833f);   // incrementing the value of C2

					for(k=0;k<up_bound_3;k+=step_){
						xn1=(xn*xn)-(yn*yn)+c1; // finding the value of next X
						yn1=(2*xn*yn)+c2;       // finding the value of next Y
						thrd=(xn1*xn1)+(yn1*yn1); // Checking the absolute value of X+Y
							if( thrd >= 64.0f ){
							//if( thrd >= 32.0f && thrd <= 150.0f ){
								length_fractal_mandelbrot += 1;							
								k=up_bound_3;
							}
						xn=xn1;
						yn=yn1;
					}
					length_fractal_mandelbrot += 1;
				}
			}		
			// end counter length array
			Log.v("Test get length_fractal_mandelbrot : ", "" + length_fractal_mandelbrot);
			Log.v("Test get length_fractal_mandelbrot*3 : ", "" + length_fractal_mandelbrot*3);
			Log.v("Test get length_fractal_mandelbrot*4 : ", "" + length_fractal_mandelbrot*4);
			
			x0=-2f; y0=-2f;
			c1=-2f; c2=-2f;
			int loop_fractal_mandelbrot=0;
			int loop_color_fractal_mandelbrot=0;
			vertices_fractal_mandelbrot=new float[(length_fractal_mandelbrot) * 3];
			vertices_color_fractal_mandelbrot=new float[(length_fractal_mandelbrot) * 4];
			
			
			for(i=0;i<up_bound_1;i+=step_){
				for(j=0;j<up_bound_2;j+=step_){
					xn = x0 + ( j * 0.00625f );  // moving from pixel to pixel horizontally..
					yn = y0 + ( i * 0.00833f ); // moving from pixel to pixel vertically..
				
					c1=-2.0f+(j*0.00625f);   // incrementing the value of C1
					c2=-2.0f+(i*0.00833f);   // incrementing the value of C2

					for(k=0;k<up_bound_3;k+=step_){
						xn1=(xn*xn)-(yn*yn)+c1; // finding the value of next X
						yn1=(2.0f*xn*yn)+c2;       // finding the value of next Y
						thrd=(xn1*xn1)+(yn1*yn1); // Checking the absolute value of X+Y
							if( thrd >= 64.0f ){
							//if( thrd >= 32.0f && thrd <= 150.0f ){
								//glColor3f(1.0, 0.0, 0.0);
								//glVertex2f(xn,yn);
								
								vertices_fractal_mandelbrot[loop_fractal_mandelbrot] = xn;
								vertices_fractal_mandelbrot[loop_fractal_mandelbrot + 1] = yn;
								vertices_fractal_mandelbrot[loop_fractal_mandelbrot + 2] = 0;
								loop_fractal_mandelbrot += 3;

								// mengenerate warna untuk setiap vertex
								vertices_color_fractal_mandelbrot[loop_color_fractal_mandelbrot] = 1.0f*yn;
								vertices_color_fractal_mandelbrot[loop_color_fractal_mandelbrot + 1] = 1.0f*xn;
								vertices_color_fractal_mandelbrot[loop_color_fractal_mandelbrot + 2] = 0.5f;
								vertices_color_fractal_mandelbrot[loop_color_fractal_mandelbrot + 3] = 1.0f;
								loop_color_fractal_mandelbrot += 4;
								
								
								k=up_bound_3;
							}

						xn=xn1;
						yn=yn1;
					}

					//glColor3f(0,1,0);
					//glVertex2f(xn,yn);
					
					vertices_fractal_mandelbrot[loop_fractal_mandelbrot] = xn;
					vertices_fractal_mandelbrot[loop_fractal_mandelbrot + 1] = yn;
					vertices_fractal_mandelbrot[loop_fractal_mandelbrot + 2] = 0;
					loop_fractal_mandelbrot += 3;

					// mengenerate warna untuk setiap vertex
					vertices_color_fractal_mandelbrot[loop_color_fractal_mandelbrot] = 0.5f*xn;
					vertices_color_fractal_mandelbrot[loop_color_fractal_mandelbrot + 1] = 1.0f;
					vertices_color_fractal_mandelbrot[loop_color_fractal_mandelbrot + 2] = 0.5f*yn;
					vertices_color_fractal_mandelbrot[loop_color_fractal_mandelbrot + 3] = 1.0f;
					loop_color_fractal_mandelbrot += 4;
				}
			}
			
			*/
				
			// ============ end to generate vertices to fractal mandelbrot 
			
			//start fractal set julia
			/*int length_fractal_julia=10000;
			vertices_fractal_julia=new float[(int) (3*length_fractal_julia) * 3];
			vertices_color_fractal_julia=new float[(int) (3*length_fractal_julia) * 4];
			
			int min_rand = 0;
			int max_rand = 10;
			float my_random;
			
			
			int idx_vertex = 0;
			int idx_vertex_color = 0;
			float k_julia=0.01f;
			
			float z0=1;
			float z_next;
			z_next=z0;
			
			//Inisialisasi bilangan komplek untuk k
			//mycomplex_k = new MyComplex(0.3, 0.6);
		    MyComplex k_complex = new MyComplex(0.3, 0.6);
		    //MyComplex k_complex = new MyComplex(0.01, 1.2);
		    //MyComplex k_complex = new MyComplex(0.109, 0.603);
		    MyComplex z0_complex = new MyComplex(1, 0);
		    MyComplex znext_complex = new MyComplex(z0_complex.re(), z0_complex.im());
		    float abs_znext_complex ;
		    float akar_abs_znext_complex;
		    float teta;
		    float delta=0.01f;
		    //for (int k=1;k<=length_fractal_julia;k++){
		    	//k_complex = new MyComplex(1/k, 2/k);
				for(i=0;i<length_fractal_julia;i++){				
					z0_complex = znext_complex;
					//znext_complex = (z0_complex.times(z0_complex)).plus(k_complex);
					
					Random init_random = new Random();
					my_random = (float)(init_random.nextInt(max_rand - min_rand + 1) + min_rand)/max_rand;
					
					if (my_random < 0.5f){					
						znext_complex=z0_complex.minus(k_complex);	
						znext_complex = new MyComplex(znext_complex.pow(0.5,2).re(),znext_complex.pow(0.5,2).im());					
					}else{
						znext_complex=z0_complex.minus(k_complex);
						znext_complex = new MyComplex(-znext_complex.pow(0.5,2).re(),-znext_complex.pow(0.5,2).im());
					}
					
					
					//Log.v("float znext_complex.im() : ", ""+(float) znext_complex.im());
					//Log.v("float znext_complex.re() : ", ""+(float) znext_complex.re());
					//Log.v("atan yx : ", ""+Math.atan(1/1));
					//Log.v("atan2 yx : ", ""+Math.atan2(1,2));
					
					
					//if(Math.pow(abs_znext_complex,2)<=4){
					
					//delta=(float) Math.abs(znext_complex.re()-znext_complex.im());
					
					vertices_fractal_julia[idx_vertex++] = (float) znext_complex.re();
					vertices_fractal_julia[idx_vertex++] = (float) znext_complex.im();
					vertices_fractal_julia[idx_vertex++] = 0;
	
					vertices_color_fractal_julia[idx_vertex_color++] = (float) Math.abs(znext_complex.re());
					vertices_color_fractal_julia[idx_vertex_color++] = (float) Math.abs(znext_complex.im());
					vertices_color_fractal_julia[idx_vertex_color++] = 1.f;		
					vertices_color_fractal_julia[idx_vertex_color++] = 0.5f;	
					
					
					vertices_fractal_julia[idx_vertex++] = (float) znext_complex.re();
					vertices_fractal_julia[idx_vertex++] = (float) znext_complex.im()+delta;
					vertices_fractal_julia[idx_vertex++] = 0;
	
					vertices_color_fractal_julia[idx_vertex_color++] = (float) Math.abs(znext_complex.re());
					vertices_color_fractal_julia[idx_vertex_color++] = (float) Math.abs(znext_complex.im());
					vertices_color_fractal_julia[idx_vertex_color++] = 1.f;		
					vertices_color_fractal_julia[idx_vertex_color++] = 0.5f;
					
					vertices_fractal_julia[idx_vertex++] = (float) znext_complex.re()+delta;
					vertices_fractal_julia[idx_vertex++] = (float) znext_complex.im()+delta;
					vertices_fractal_julia[idx_vertex++] = 0;
	
					vertices_color_fractal_julia[idx_vertex_color++] = (float) Math.abs(znext_complex.re());
					vertices_color_fractal_julia[idx_vertex_color++] = (float) Math.abs(znext_complex.im());
					vertices_color_fractal_julia[idx_vertex_color++] = 1.f;		
					vertices_color_fractal_julia[idx_vertex_color++] = 0.5f;
					
					//segitiga dua
					/*vertices_fractal_julia[idx_vertex++] = (float) znext_complex.re();
					vertices_fractal_julia[idx_vertex++] = (float) znext_complex.im();
					vertices_fractal_julia[idx_vertex++] = 0;
	
					vertices_color_fractal_julia[idx_vertex_color++] = (float) Math.abs(znext_complex.re());
					vertices_color_fractal_julia[idx_vertex_color++] = (float) Math.abs(znext_complex.im());
					vertices_color_fractal_julia[idx_vertex_color++] = 1.f;		
					vertices_color_fractal_julia[idx_vertex_color++] = 0.5f;	
					
					
					vertices_fractal_julia[idx_vertex++] = (float) znext_complex.re()+delta;
					vertices_fractal_julia[idx_vertex++] = (float) znext_complex.im();
					vertices_fractal_julia[idx_vertex++] = 0;
	
					vertices_color_fractal_julia[idx_vertex_color++] = (float) Math.abs(znext_complex.re());
					vertices_color_fractal_julia[idx_vertex_color++] = (float) Math.abs(znext_complex.im());
					vertices_color_fractal_julia[idx_vertex_color++] = 1.f;		
					vertices_color_fractal_julia[idx_vertex_color++] = 0.5f;
					
					vertices_fractal_julia[idx_vertex++] = (float) znext_complex.re()+delta;
					vertices_fractal_julia[idx_vertex++] = (float) znext_complex.im()+delta;
					vertices_fractal_julia[idx_vertex++] = 0;
	
					vertices_color_fractal_julia[idx_vertex_color++] = (float) Math.abs(znext_complex.re());
					vertices_color_fractal_julia[idx_vertex_color++] = (float) Math.abs(znext_complex.im());
					vertices_color_fractal_julia[idx_vertex_color++] = 1.f;		
					vertices_color_fractal_julia[idx_vertex_color++] = 0.5f;
					*/
					
					//}
					
					
					//vertices_color_fractal_julia[idx_vertex_color++] = 1/z0;
					//vertices_color_fractal_julia[idx_vertex_color++] = 1/z_next;
					//vertices_color_fractal_julia[idx_vertex_color++] = 1/(z0-z_next);		
					//vertices_color_fractal_julia[idx_vertex_color++] = 1.f;	
					
				//}
		    //}
	 		
			
			//end fractal set julia
				
				
		   // start merget fractal julia & mandelbrot
			//int length_fractal_julia_m=width*height;
			//vertices_fractal_julia_m=new float[(int) (6*length_fractal_julia_m) * 3];
			//vertices_color_fractal_julia_m=new float[(int) (6*length_fractal_julia_m) * 4];
			/*
		    float left = -2.0f, right = 2.0f, bottom = -2.0f;//, top = 1.5f;
				// loop over the pixels on the screen
				float delta = (right - left)/width;
				  for( float j_l=0; j_l < 100; j_l+=1f )
				  {      
				      for( float i_l=0; i_l < 100; i_l+=1f )
				      {
				        // convert pixel location to world coordinates
				        float xf = left + i_l*delta;
				        float yf = bottom + j_l*delta;   
				        
				        //float xf = i;
				        //float yf = j;  
				        
				        // test for convergence
				        //int its = 0;
				        //float R = 0;
				        MyComplex b_ = new MyComplex(xf,yf);
				        MyComplex c = new MyComplex(0.109, 0.603);
				        if( !doJuliaSet ){
				        julia(b_, c);}
				      else{
				          mandelbrot(b_);}

				        // turn iterations and radius to color
				      
				        if( its_loop ==256  ){
				        	vertices_color_fractal_julia_m[idx_vertex_color++]=0;
				        	vertices_color_fractal_julia_m[idx_vertex_color++]=0;
				        	vertices_color_fractal_julia_m[idx_vertex_color++]=0;
				        	vertices_color_fractal_julia_m[idx_vertex_color++]=1;
				        	
				        	vertices_color_fractal_julia_m[idx_vertex_color++]=0;
				        	vertices_color_fractal_julia_m[idx_vertex_color++]=0;
				        	vertices_color_fractal_julia_m[idx_vertex_color++]=0;
				        	vertices_color_fractal_julia_m[idx_vertex_color++]=1;
				        	
				        	vertices_color_fractal_julia_m[idx_vertex_color++]=0;
				        	vertices_color_fractal_julia_m[idx_vertex_color++]=0;
				        	vertices_color_fractal_julia_m[idx_vertex_color++]=0;
				        	vertices_color_fractal_julia_m[idx_vertex_color++]=1;
				        	
				        	vertices_color_fractal_julia_m[idx_vertex_color++]=0;
				        	vertices_color_fractal_julia_m[idx_vertex_color++]=0;
				        	vertices_color_fractal_julia_m[idx_vertex_color++]=0;
				        	vertices_color_fractal_julia_m[idx_vertex_color++]=1;
				        	
				        	vertices_color_fractal_julia_m[idx_vertex_color++]=0;
				        	vertices_color_fractal_julia_m[idx_vertex_color++]=0;
				        	vertices_color_fractal_julia_m[idx_vertex_color++]=0;
				        	vertices_color_fractal_julia_m[idx_vertex_color++]=1;
				        	
				        	vertices_color_fractal_julia_m[idx_vertex_color++]=0;
				        	vertices_color_fractal_julia_m[idx_vertex_color++]=0;
				        	vertices_color_fractal_julia_m[idx_vertex_color++]=0;
				        	vertices_color_fractal_julia_m[idx_vertex_color++]=1;
				        	 
				        	
				        }else
				        {
				        	
				        	
				        	float r = r_julia_m/(3.f);
					          float g = its_loop/(128.f);
					          float b = r_julia_m/(its_loop+1.f);
					          //glColor3d(r,g,b);
					          vertices_color_fractal_julia_m[idx_vertex_color++]=r;
				        	  vertices_color_fractal_julia_m[idx_vertex_color++]=g;
				        	  vertices_color_fractal_julia_m[idx_vertex_color++]=b;
				        	  vertices_color_fractal_julia_m[idx_vertex_color++]=0.5f;
				        	  
				        	  vertices_color_fractal_julia_m[idx_vertex_color++]=r;
				        	  vertices_color_fractal_julia_m[idx_vertex_color++]=g;
				        	  vertices_color_fractal_julia_m[idx_vertex_color++]=b;
				        	  vertices_color_fractal_julia_m[idx_vertex_color++]=0.5f;
				        	  
				        	  vertices_color_fractal_julia_m[idx_vertex_color++]=r;
				        	  vertices_color_fractal_julia_m[idx_vertex_color++]=g;
				        	  vertices_color_fractal_julia_m[idx_vertex_color++]=b;
				        	  vertices_color_fractal_julia_m[idx_vertex_color++]=0.5f;
				        	  
				        	  vertices_color_fractal_julia_m[idx_vertex_color++]=r;
				        	  vertices_color_fractal_julia_m[idx_vertex_color++]=g;
				        	  vertices_color_fractal_julia_m[idx_vertex_color++]=b;
				        	  vertices_color_fractal_julia_m[idx_vertex_color++]=0.5f;
				        	  
				        	  vertices_color_fractal_julia_m[idx_vertex_color++]=r;
				        	  vertices_color_fractal_julia_m[idx_vertex_color++]=g;
				        	  vertices_color_fractal_julia_m[idx_vertex_color++]=b;
				        	  vertices_color_fractal_julia_m[idx_vertex_color++]=0.5f;
				        	  
				        	  vertices_color_fractal_julia_m[idx_vertex_color++]=r;
				        	  vertices_color_fractal_julia_m[idx_vertex_color++]=g;
				        	  vertices_color_fractal_julia_m[idx_vertex_color++]=b;
				        	  vertices_color_fractal_julia_m[idx_vertex_color++]=0.5f;
				        }  
				        	  //delta = 0.0001f;
				        	  
				        	  vertices_fractal_julia_m[idx_vertex++]=xf;
						        vertices_fractal_julia_m[idx_vertex++]=yf;
						        vertices_fractal_julia_m[idx_vertex++]=0;
						        
						        vertices_fractal_julia_m[idx_vertex++]=xf;
						        vertices_fractal_julia_m[idx_vertex++]=yf+delta;
						        vertices_fractal_julia_m[idx_vertex++]=0;
						        
						        vertices_fractal_julia_m[idx_vertex++]=xf+delta;
						        vertices_fractal_julia_m[idx_vertex++]=yf+delta;
						        vertices_fractal_julia_m[idx_vertex++]=0;
						        
						        vertices_fractal_julia_m[idx_vertex++]=xf;
						        vertices_fractal_julia_m[idx_vertex++]=yf;
						        vertices_fractal_julia_m[idx_vertex++]=0;
						        
						        vertices_fractal_julia_m[idx_vertex++]=xf+delta;
						        vertices_fractal_julia_m[idx_vertex++]=yf+delta;
						        vertices_fractal_julia_m[idx_vertex++]=0;
						        
						        vertices_fractal_julia_m[idx_vertex++]=xf+delta;
						        vertices_fractal_julia_m[idx_vertex++]=yf;
						        vertices_fractal_julia_m[idx_vertex++]=0;
				        //}

				        // draw polygon
				        //glBegin(GL_POLYGON);
				        //glVertex2d(x, y);
				        //glVertex2d(x, y+delta);
				        //glVertex2d(x+delta, y+delta);
				        //glVertex2d(x+delta, y);
				        //glEnd();
				        
				        
				       
				      }
				   // glFlush();
				  } 
				  */
		   // end merger fractal julia & mandelbrot
	     
	     
	}
	
	//--- start merger julia & Mandelbrot fractal -----------------
	/*public void julia( MyComplex p, MyComplex c)
	{
	  float rSqr = 0;
	  int maxIterations = 256;
	  for(its_loop=0; its_loop < maxIterations; its_loop++ )
	  {
	    //p = p.times(p).plus(c);
		  //Log.v("its_loop julia : ", ""+its_loop);
	    p = new MyComplex(p.times(p).plus(c).re(),p.times(p).plus(c).im());
	    rSqr = (float) (p.re()*p.re() + p.im()*p.im());
	    //Log.v("rSqr julia : ", ""+rSqr);
	    if( rSqr > 4 ){
	      break;
	    }
	  }
	  r_julia_m = (float) Math.sqrt(rSqr);
	}
	
	public void mandelbrot( MyComplex b_)
	{
	  float rSqr = 0;
	  int maxIterations = 256;
	  MyComplex p = new MyComplex(0,0);
	  for(its_loop=0; its_loop < maxIterations; its_loop++ )
	  {
	    //p = p.times(p).plus(c);
		//Log.v("its_loop mandelbrot : ", ""+its_loop);
	    p = new MyComplex(p.times(p).plus(b_).re(),p.times(p).plus(b_).im());
	    rSqr = (float) (p.re()*p.re() + p.im()*p.im());
	    //Log.v("rSqr mandelbrot : ", ""+rSqr);
	    if( rSqr > 4 ){
	      break;
	    }
	  }
	  r_julia_m = (float) Math.sqrt(rSqr);
	}*/
	
	// ------------- end Julia & Mandelbrol Fractal --------------------
	
	// membuat inisialisasi jaring-jaring (generate  Maze)
	public void generateMaze()
	{
	    //inisialisasi maze
	    int i,j;
	    Log.v("Test get numberRow : ", "" + numberRow);
	    Log.v("Test get numberCol : ", "" + numberCol);
	    for (i = 0; i < numberRow; i++){
	         for (j = 0; j < numberCol; j++)
	         {
	             maze[i][j] = 0;
	             verti[i][j] = 1;
	             hori[i][j] = 1; 
	         }   
	    }
	    for (i = 0; i < numberRow; i++){ verti[i][numberCol] = 1;}
	    for (j = 0; j < numberCol; j++){ hori[numberRow][j] = 1;}
	     
	     //random cell
	     i = (int) (Math.random()*10%numberRow);    
	     j = (int) (Math.random()*10%numberCol);
	     track(i,j);
	     
	     //create in & out door
	     i = (int) (Math.random()*10%numberRow);
	     j = (int) (Math.random()*10%numberRow);
	     
	     verti[i][0] = 0; verti[j][numberCol] = 0;
	     
	  // first position triangle
	     positionRow = i; 
	     positionCol = 0;
	     positionRow_end = j; 
	     positionCol_end = numberCol;
	     Navigate = 0;
	}
	
	//rekursif
	public void track(int RowTrack, int ColTrack)  
	{
	     int i,j;
	     while (CheckNeighbor(RowTrack,ColTrack))
	     {
	           do
	           {    //random cell to check neighbor that not yet visit
	                i = RowTrack; j = ColTrack;
	                //int k = rand()%4;
	                int k = (int) (Math.random()*10%4);
	                if (k==0) j = ColTrack-1;
	                else if (k==1) i = RowTrack-1;
	                else if (k==2) j = ColTrack+1;
	                else i = RowTrack+1;                      
	           }while(i<0 || i>=numberRow || j<0 || j>=numberCol || maze[i][j]==1);
	           
	           maze[i][j] = 1;   //mark cell that has been visit
	           RemoveLine(RowTrack,ColTrack,i,j);  
	           track(i,j);           
	     }
	}
	
	public boolean CheckNeighbor(int i, int j)
	{
	     if ((i-1 >=0) && (maze[i-1][j]==0)) return true;
	     if ((i+1 <numberRow) && (maze[i+1][j]==0)) return true;
	     if ((j-1 >=0) && (maze[i][j-1]==0)) return true;
	     if ((j+1 <numberCol) && (maze[i][j+1]==0)) return true;
	     return false;      
	}
	
	//remove line between 2 cell
	public void RemoveLine(int a1,int b1, int a2, int b2)  
	{                                                        
	     if(a1==a2) 
	     {
	    	 //this.x_player_end=a1-(WidthObject/2);
	    	 
	          if(b1 > b2) {
	        	  verti[a1][b1] = 0;
	        	  //this.y_player_end=b2-(WidthObject/2);
	          }
	          else { 
	        	  verti[a1][b2] = 0;	
	        	  //this.y_player_end=b1-(WidthObject/2);
	          }
	     }
	     else if(b1==b2) 
	     {
	          if(a1 > a2) hori[a1][b1] = 0;
	          else hori[a2][b1] = 0;
	     }
	}
	
	// Point to our vertex buffer, return buffer holding the vertices
	public static FloatBuffer makeFloatBuffer(float[] arr) {
		ByteBuffer bb = ByteBuffer.allocateDirect(arr.length * 4);
		bb.order(ByteOrder.nativeOrder());
		FloatBuffer fb = bb.asFloatBuffer();
		fb.put(arr);
		fb.position(0);
		return fb;
	}

	/** The draw method for the primitive object with the GL context */
	public void draw_background(GL10 gl) {
		//menempelkan tekstur ke objek
		gl.glEnable(GL10.GL_TEXTURE_2D);
		    
		// bind the previously generated texture
		gl.glBindTexture(GL10.GL_TEXTURE_2D, textures_indek[0]);
		System.out.println(textures_indek[0]);
		
		// Point to our buffers
		gl.glEnableClientState(GL10.GL_VERTEX_ARRAY);
		gl.glEnableClientState(GL10.GL_TEXTURE_COORD_ARRAY);
		
		gl.glColor4f(1.0f, 1.0f, 1.0f, 1.0f);
		
		// Set the face rotation
		gl.glFrontFace(GL10.GL_CW);
		
		// Point to our vertex buffer
		gl.glVertexPointer(3, GL10.GL_FLOAT, 0, makeFloatBuffer(verticesbackground));
		gl.glTexCoordPointer(3, GL10.GL_FLOAT, 0, makeFloatBuffer(texturebackground));
		
		// Draw the vertices as triangle strip
		gl.glDrawArrays(GL10.GL_TRIANGLE_STRIP, 0, verticesbackground.length / 3);
		//Log.v("verticesbackground.length : ", ""+verticesbackground.length);
		//Log.v("verticesbackground.length / 3 : ", ""+verticesbackground.length / 3);
		//gl.glDrawArrays(GL10.GL_TRIANGLE_FAN, 0, verticesbackground.length / 3);

		//Disable the client state before leaving
		gl.glDisableClientState(GL10.GL_VERTEX_ARRAY);
		gl.glDisableClientState(GL10.GL_TEXTURE_COORD_ARRAY);
		gl.glDisable( GL10.GL_TEXTURE_2D );             // Disable Texture Mapping
	}
	public void draw_points(GL10 gl) {

		gl.glEnableClientState(GL10.GL_VERTEX_ARRAY);
		// gl.glEnableClientState(GL10.GL_COLOR_ARRAY);

		// set the colour for the points (pemberian warna untuk titik)
		gl.glColor4f(0.0f, 0.0f, 1.0f, 1.0f);

		// Point to our vertex buffer (mendata nilai lokasi/posisi titik)
		gl.glVertexPointer(3, GL10.GL_FLOAT, 0, makeFloatBuffer(new float[] {
				1.0f, 1.0f, 0.0f, // V1 - first vertex (x,y,z)
				1.0f, 0.8f, 0.0f, // V2
				1.0f, 0.6f, 0.0f, // V3
				1.0f, 0.4f, 0.0f, // V4
				1.0f, 0.2f, 0.0f, // V5
				1.0f, 0.0f, 0.0f, // V6
				1.0f, -0.2f, 0.0f, // V7
				1.0f, -0.4f, 0.0f, // V8
				1.0f, -0.6f, 0.0f, // V9
				1.0f, -0.8f, 0.0f, // V10
				1.0f, -1.0f, 0.0f, // V11

				0.8f, -1.0f, 0.0f, // V12
				0.6f, -1.0f, 0.0f, // V13
				0.4f, -1.0f, 0.0f, // V14
				0.2f, -1.0f, 0.0f, // V15
				0.0f, -1.0f, 0.0f, // V16
				-0.2f, -1.0f, 0.0f, // V17
				-0.4f, -1.0f, 0.0f, // V18
				-0.6f, -1.0f, 0.0f, // V19
				-0.7f, -1.0f, 0.0f, // V20
				-0.8f, -1.0f, 0.0f, // V21
				-1.0f, -1.0f, 0.0f, // V22
		}));

		// Draw the vertices as points (menggambar titik-titik)
		gl.glDrawArrays(GL10.GL_POINTS, 0, 22);

		// Disable the client state before leaving
		gl.glDisableClientState(GL10.GL_VERTEX_ARRAY);
		// gl.glDisableClientState(GL10.GL_COLOR_ARRAY);

	}
	
	public void draw_fractal_julia_m(GL10 gl) {
		//gl.glFrontFace(GL10.GL_CW); // Front face in counter-clockwise
		gl.glEnableClientState(GL10.GL_VERTEX_ARRAY);
	    gl.glEnableClientState(GL10.GL_COLOR_ARRAY);

		// set the colour for the points (pemberian warna untuk titik)
	    //gl.glColor4f(0.0f, 0.0f, 1.0f, 0.5f);
		
		

		// Point to our vertex buffer (mendata nilai lokasi/posisi titik)
		gl.glVertexPointer(3, GL10.GL_FLOAT, 0, makeFloatBuffer(vertices_fractal_julia_m));

		gl.glColorPointer(4, GL10.GL_FLOAT, 0, makeFloatBuffer(vertices_color_fractal_julia_m));
		// Draw the vertices as points (menggambar titik-titik)
		gl.glDrawArrays(GL10.GL_POINTS, 0, (int) ((idx_vertex-1)/3));
		//gl.glDrawArrays(GL10.GL_TRIANGLE_STRIP, 0, (int) ((idx_vertex-1)/3));
		//gl.glDrawArrays(GL10.GL_POINTS, 0, 3);
		//gl.glDrawArrays(GL10.GL_TRIANGLE_STRIP, 0, (int) (vertices_fractal_julia_m.length)/3);
		//gl.glDrawArrays(GL10.GL_TRIANGLE_STRIP, 2,3);
		//gl.glDrawArrays(GL10.GL_LINES, 0, (int) (vertices_fractal_julia.length)/3);
		
		//gl.glDrawArrays(GLES20.GL_POLYGON_OFFSET_FILL, 0, (int) (vertices_fractal_julia.length)/3);

		// Disable the client state before leaving
		gl.glDisableClientState(GL10.GL_VERTEX_ARRAY);
		gl.glDisableClientState(GL10.GL_COLOR_ARRAY);

	}
	
	public void draw_fractal_julia(GL10 gl) {

		gl.glEnableClientState(GL10.GL_VERTEX_ARRAY);
	    gl.glEnableClientState(GL10.GL_COLOR_ARRAY);

		// set the colour for the points (pemberian warna untuk titik)
		//gl.glColor4f(0.0f, 0.0f, 1.0f, 1.0f);
		
		

		// Point to our vertex buffer (mendata nilai lokasi/posisi titik)
		gl.glVertexPointer(3, GL10.GL_FLOAT, 0, makeFloatBuffer(vertices_fractal_julia));

		gl.glColorPointer(4, GL10.GL_FLOAT, 0, makeFloatBuffer(vertices_color_fractal_julia));
		// Draw the vertices as points (menggambar titik-titik)
		gl.glDrawArrays(GL10.GL_POINTS, 0, (int) (vertices_fractal_julia.length)/3);
		//gl.glDrawArrays(GL10.GL_TRIANGLE_STRIP, 0, (int) (vertices_fractal_julia.length)/3);
		//gl.glDrawArrays(GL10.GL_LINES, 0, (int) (vertices_fractal_julia.length)/3);
		
		//gl.glDrawArrays(GLES20.GL_POLYGON_OFFSET_FILL, 0, (int) (vertices_fractal_julia.length)/3);

		// Disable the client state before leaving
		gl.glDisableClientState(GL10.GL_VERTEX_ARRAY);
		gl.glDisableClientState(GL10.GL_COLOR_ARRAY);

	}
	
	public void draw_fractal_mandelbrot(GL10 gl) {

		gl.glEnableClientState(GL10.GL_VERTEX_ARRAY);
	    gl.glEnableClientState(GL10.GL_COLOR_ARRAY);

		// set the colour for the points (pemberian warna untuk titik)
		//gl.glColor4f(0.0f, 0.0f, 1.0f, 1.0f);
		
		

		// Point to our vertex buffer (mendata nilai lokasi/posisi titik)
		gl.glVertexPointer(3, GL10.GL_FLOAT, 0, makeFloatBuffer(vertices_fractal_mandelbrot));

		gl.glColorPointer(4, GL10.GL_FLOAT, 0, makeFloatBuffer(vertices_color_fractal_mandelbrot));
		// Draw the vertices as points (menggambar titik-titik)
		gl.glDrawArrays(GL10.GL_POINTS, 0, (int) (vertices_fractal_mandelbrot.length)/3);

		// Disable the client state before leaving
		gl.glDisableClientState(GL10.GL_VERTEX_ARRAY);
		gl.glDisableClientState(GL10.GL_COLOR_ARRAY);

	}

	public void draw_line_maze_horiverti(GL10 gl) {
		gl.glEnableClientState(GL10.GL_VERTEX_ARRAY);
		//gl.glEnableClientState(GL10.GL_COLOR_ARRAY);

		// set the colour for the line (pemberian warna untuk garis)
		gl.glColor4f(0.5f, 0.1f, 0.3f, 1.0f);	
		//gl.glColor4f(1.0f, 1.0f, 1.0f, 1.0f);
		gl.glVertexPointer(3, GL10.GL_FLOAT, 0, makeFloatBuffer(vertices_horiverti));		
		gl.glDrawArrays(GL10.GL_LINES,0, (int) (vertices_horiverti.length/3));

		// Disable the client state before leaving
		gl.glDisableClientState(GL10.GL_VERTEX_ARRAY);
		//gl.glDisableClientState(GL10.GL_COLOR_ARRAY);

	}
	
	
	public void draw_segitiga_labirin_player(GL10 gl, int positionCol_in, int positionRow_in) {
		
		 vertices_labirin_player[0]=(positionCol_in * WidthObject) + (WidthObject/2);
	     vertices_labirin_player[1]=(numberRow - positionRow_in - 1)*WidthObject + (WidthObject * 3 / 4);
	     vertices_labirin_player[2]=0;
	     
	     vertices_labirin_player[3]=(positionCol_in * WidthObject) + (WidthObject/4);	     
	     vertices_labirin_player[4]=(numberRow - positionRow_in - 1)*WidthObject + (WidthObject / 4);
	     vertices_labirin_player[5]=0;
	     
	     vertices_labirin_player[6]=(positionCol_in * WidthObject) + (WidthObject*3/4);	     
	     vertices_labirin_player[7]=(numberRow - positionRow_in - 1)*WidthObject + (WidthObject / 4);
	     vertices_labirin_player[8]=0;

	     //x = (positionCol * WidthObject) + (WidthObject/2);
	     //y = (numberRow - positionRow - 1)*WidthObject + (WidthObject / 2);
		
		
		gl.glEnableClientState(GL10.GL_VERTEX_ARRAY);
		gl.glEnableClientState(GL10.GL_COLOR_ARRAY);

		// set the colour for the triangle
		//gl.glColor4f(1.0f, 0.0f, 0.0f, 1.0f);

		gl.glVertexPointer(3, GL10.GL_FLOAT, 0, makeFloatBuffer(vertices_labirin_player));

		// Draw the vertices as triangle
		gl.glColorPointer(4, GL10.GL_FLOAT, 0, makeFloatBuffer(vertices_color));
		gl.glDrawArrays(GLES20.GL_TRIANGLES, 0, 3);

		// Disable the client state before leaving
		gl.glDisableClientState(GL10.GL_VERTEX_ARRAY);
		gl.glDisableClientState(GL10.GL_COLOR_ARRAY);

	}
	
	public void draw_segitiga_labirin_player(GL10 gl ) {
		gl.glEnableClientState(GL10.GL_VERTEX_ARRAY);
		gl.glEnableClientState(GL10.GL_COLOR_ARRAY);

		// set the colour for the triangle
		//gl.glColor4f(1.0f, 0.0f, 0.0f, 1.0f);

		gl.glVertexPointer(3, GL10.GL_FLOAT, 0, makeFloatBuffer(vertices_labirin_player));

		// Draw the vertices as triangle
		gl.glColorPointer(4, GL10.GL_FLOAT, 0, makeFloatBuffer(vertices_color));
		gl.glDrawArrays(GLES20.GL_TRIANGLES, 0, 3);

		// Disable the client state before leaving
		gl.glDisableClientState(GL10.GL_VERTEX_ARRAY);
		gl.glDisableClientState(GL10.GL_COLOR_ARRAY);

	}
	
	public void draw_segitiga_texture(GL10 gl, int positionCol_in, int positionRow_in) {
		gl.glFrontFace(GL10.GL_CCW); // Front face in counter-clockwise
		gl.glEnableClientState(GL10.GL_VERTEX_ARRAY);
		//gl.glEnableClientState(GL10.GL_COLOR_ARRAY);
		
		vertices_labirin_player[0]=(positionCol_in * WidthObject) + (WidthObject/2);
	     vertices_labirin_player[1]=(numberRow - positionRow_in - 1)*WidthObject + (WidthObject * 3 / 4);
	     vertices_labirin_player[2]=0;
	     
	     vertices_labirin_player[3]=(positionCol_in * WidthObject) + (WidthObject/4);	     
	     vertices_labirin_player[4]=(numberRow - positionRow_in - 1)*WidthObject + (WidthObject / 4);
	     vertices_labirin_player[5]=0;
	     
	     vertices_labirin_player[6]=(positionCol_in * WidthObject) + (WidthObject*3/4);	     
	     vertices_labirin_player[7]=(numberRow - positionRow_in - 1)*WidthObject + (WidthObject / 4);
	     vertices_labirin_player[8]=0;
		
		// set the colour for the triangle (menghilangkan effect dari warna objek sebelumnya)
		gl.glColor4f(1.0f, 1.0f, 1.0f, 1.0f);
		
		/*gl.glVertexPointer(3, GL10.GL_FLOAT, 0, makeFloatBuffer(new float [] {
				1.0f, 1.0f,  0.0f,		// V3
				0.0f, 1.0f,  0.0f,		// V2
				0.5f, 0.0f,  0.0f,	// V1 - first vertex (x,y,z)
		}));*/
		
		gl.glVertexPointer(3, GL10.GL_FLOAT, 0, makeFloatBuffer(vertices_labirin_player));	

		// Draw the vertices as triangle
		//gl.glColorPointer(4, GL10.GL_FLOAT, 0, makeFloatBuffer(vertices_color));
		
		//menempelkan tekstur ke objek
	    gl.glEnable(GL10.GL_TEXTURE_2D);

		gl.glEnable(GL10.GL_BLEND);
		gl.glBlendFunc(GL10.GL_SRC_ALPHA, GL10.GL_ONE_MINUS_SRC_ALPHA);

		gl.glBindTexture(GL10.GL_TEXTURE_2D, textures_indek[0]); // 
		gl.glTexCoordPointer(3, GL10.GL_FLOAT, 0, makeFloatBuffer(textCoord_Triagle)); // 		
		gl.glEnableClientState(GL10.GL_TEXTURE_COORD_ARRAY);
		
		gl.glDrawArrays(GLES20.GL_TRIANGLE_STRIP, 0, 3);
		
		//Disable the client state before leaving
		gl.glDisableClientState(GL10.GL_VERTEX_ARRAY);
		//gl.glDisableClientState(GL10.GL_COLOR_ARRAY);
		gl.glDisable( GL10.GL_BLEND );                  // Disable Alpha Blend
	    gl.glDisable( GL10.GL_TEXTURE_2D );             // Disable Texture Mapping

	}

	public void draw_line(GL10 gl) {

		gl.glEnableClientState(GL10.GL_VERTEX_ARRAY);
		// gl.glEnableClientState(GL10.GL_COLOR_ARRAY);

		// set the colour for the line (pemberian warna untuk garis)
		gl.glColor4f(0.0f, 0.0f, 1.0f, 1.0f);

		// Point to our vertex buffer (mendata nilai lokasi/posisi titik yang
		// menyusun garis)
		gl.glVertexPointer(3, GL10.GL_FLOAT, 0, makeFloatBuffer(new float[] {
				1.0f, 1.0f, 0.0f, // V1 - first vertex (x,y,z)
				-1.0f, -1.0f, 0.0f, // V2 - second vertex
		}));

		// Draw the vertices as lines (menggambar garis dari titik-titik)
		gl.glDrawArrays(GL10.GL_LINES, 0, 2);
		/*
		 * gl.glDrawElements(GL10.GL_LINES, 2, GL10.GL_UNSIGNED_SHORT,
		 * makeFloatBuffer(new float [] { 1.0f, 1.0f, 0.0f, // V1 - first vertex
		 * (x,y,z) -1.0f, -1.0f, 0.0f, // V2 - second vertex }));
		 */

		// Disable the client state before leaving
		gl.glDisableClientState(GL10.GL_VERTEX_ARRAY);
		// gl.glDisableClientState(GL10.GL_COLOR_ARRAY);

	}

	public void draw_line_color(GL10 gl) {

		gl.glEnableClientState(GL10.GL_VERTEX_ARRAY);
		gl.glEnableClientState(GL10.GL_COLOR_ARRAY);

		// set the colour for the line (pemberian warna untuk garis)
		gl.glColor4f(0.0f, 0.0f, 1.0f, 1.0f);

		// Point to our vertex buffer (mendata nilai lokasi/posisi titik yang
		// menyusun garis)
		gl.glVertexPointer(3, GL10.GL_FLOAT, 0, makeFloatBuffer(vertices_line));

		// memetakan warna untuk setiap vertex
		gl.glColorPointer(4, GL10.GL_FLOAT, 0,
				makeFloatBuffer(vertices_line_color));

		// Draw the vertices as lines (menggambar garis dari titik-titik)
		gl.glDrawArrays(GL10.GL_LINE_STRIP, 0,
				(int) (2 * (x2 - x1) / step_line));
		/*
		 * gl.glDrawElements(GL10.GL_LINES, 2, GL10.GL_UNSIGNED_SHORT,
		 * makeFloatBuffer(new float [] { 1.0f, 1.0f, 0.0f, // V1 - first vertex
		 * (x,y,z) -1.0f, -1.0f, 0.0f, // V2 - second vertex }));
		 */

		// Disable the client state before leaving
		gl.glDisableClientState(GL10.GL_VERTEX_ARRAY);
		gl.glDisableClientState(GL10.GL_COLOR_ARRAY);

	}

	public void draw_circle(GL10 gl) {
		gl.glEnableClientState(GL10.GL_VERTEX_ARRAY);
		// gl.glEnableClientState(GL10.GL_COLOR_ARRAY);

		// set the colour for the object circle
		gl.glColor4f(1.0f, 0.0f, 0.0f, 1.0f);

		// create VBO from buffer with glBufferData()
		gl.glVertexPointer(3, GL10.GL_FLOAT, 0,
				makeFloatBuffer(vertices_circle));

		// draw circle as filled shape
		// gl.glDrawArrays(GL10.GL_TRIANGLE_FAN, 1, (int) ((int)
		// 2*batas_sudut/step));

		// draw circle contours
		// gl.glDrawArrays(GL10.GL_LINES, 1, (int) ((int) 2*batas_sudut/step));
		// // membuat garis putus-putus pada tepi lingkaran
		gl.glDrawArrays(GL10.GL_LINES, 1, (int) ((int) 2 * batas_sudut / step));
		// gl.glDrawArrays(GL10.GL_LINE_STRIP, 1, (int) ((int)
		// 2*batas_sudut/step));
		// gl.glDrawArrays(GL10.GL_POINTS, 1, (int) ((int) 2*batas_sudut/step));

		// Disable the client state before leaving
		gl.glDisableClientState(GL10.GL_VERTEX_ARRAY);
		// gl.glDisableClientState(GL10.GL_COLOR_ARRAY);
	}

	public void draw_circle_color(GL10 gl) {
		gl.glEnableClientState(GL10.GL_VERTEX_ARRAY);
		gl.glEnableClientState(GL10.GL_COLOR_ARRAY);

		// set the colour edge for the object circle
		gl.glColor4f(0.0f, 0.0f, 1.0f, 1.0f);

		// create VBO from buffer with glBufferData()
		gl.glVertexPointer(3, GL10.GL_FLOAT, 0,
				makeFloatBuffer(vertices_circle));

		// memetakan warna untuk setiap vertex
		gl.glColorPointer(4, GL10.GL_FLOAT, 0,
				makeFloatBuffer(vertices_circle_color));

		// draw circle as filled shape
		// gl.glDrawArrays(GL10.GL_TRIANGLE_FAN, 1, (int) ((int)
		// 2*batas_sudut/step));
		gl.glDrawArrays(GL10.GL_LINE_STRIP, 1,
				(int) ((int) 2 * batas_sudut / step));

		// Disable the client state before leaving
		gl.glDisableClientState(GL10.GL_VERTEX_ARRAY);
		gl.glDisableClientState(GL10.GL_COLOR_ARRAY);
	}

	public void draw_kotak(GL10 gl) {

		gl.glEnableClientState(GL10.GL_VERTEX_ARRAY);
		gl.glEnableClientState(GL10.GL_COLOR_ARRAY);

		// Point to our vertex buffer
		gl.glVertexPointer(3, GL10.GL_FLOAT, 0, makeFloatBuffer(vertices));

		// Draw the vertices as square
		gl.glColorPointer(4, GL10.GL_FLOAT, 0, makeFloatBuffer(vertices_color));
		gl.glDrawArrays(GL10.GL_TRIANGLES, 0, 3);

		gl.glColorPointer(4, GL10.GL_FLOAT, 0, makeFloatBuffer(vertices_color));
		gl.glDrawArrays(GL10.GL_TRIANGLES, 2, 3);

		// Disable the client state before leaving
		gl.glDisableClientState(GL10.GL_VERTEX_ARRAY);
		gl.glDisableClientState(GL10.GL_COLOR_ARRAY);

	}

	public void draw_segitiga(GL10 gl) {
		gl.glEnableClientState(GL10.GL_VERTEX_ARRAY);
		gl.glEnableClientState(GL10.GL_COLOR_ARRAY);

		// set the colour for the triangle
		gl.glColor4f(1.0f, 0.0f, 0.0f, 1.0f);

		gl.glVertexPointer(3, GL10.GL_FLOAT, 0, makeFloatBuffer(new float[] {
				-0.5f, -0.5f, 0.0f, // V1 - first vertex (x,y,z)
				0.5f, -0.5f, 0.0f, // V2 - second vertex
				0.0f, 0.5f, 0.0f // V3 - third vertex

				}));

		// Draw the vertices as triangle
		gl.glColorPointer(4, GL10.GL_FLOAT, 0, makeFloatBuffer(vertices_color));
		gl.glDrawArrays(GLES20.GL_TRIANGLES, 0, 3);

		// Disable the client state before leaving
		gl.glDisableClientState(GL10.GL_VERTEX_ARRAY);
		gl.glDisableClientState(GL10.GL_COLOR_ARRAY);

	}

	// Render the shape quad
	public void draw_quad(GL10 gl) {
		// Enable vertex-array and define its buffer
		gl.glEnableClientState(GL10.GL_VERTEX_ARRAY);
		gl.glVertexPointer(3, GL10.GL_FLOAT, 0, makeFloatBuffer(vertices_quad));
		// Draw the primitives from the vertex-array directly
		gl.glPolygonOffset(0.0f, 1.0f);
		gl.glScalef(0.5f, 0.5f, 0.5f);
		gl.glColor4f(0.5f, 0.5f, 1.0f, 1.0f); // Set the current color (NEW)
		gl.glDrawArrays(GL10.GL_TRIANGLE_STRIP, 0, vertices_quad.length / 3);
		//gl.glDrawArrays(GL10.GL_TRIANGLES, 0, vertices_quad.length / 3);
		//gl.glDrawArrays(GL10.GL_LINES, 0, vertices_quad.length / 3);
		
		gl.glDisableClientState(GL10.GL_VERTEX_ARRAY);
	}
	
	public void loadBallTexture(GL10 gl, Context context,int index_Texture) {
		// Bitmap bitmap = BitmapFactory.decodeResource(context.getResources(),
		// resource);

		/*Bitmap bitmap = BitmapFactory.decodeStream(context.getResources()
				.openRawResource(R.drawable.nature));*/
		
		Bitmap bitmap = BitmapFactory.decodeStream(context.getResources()
				.openRawResource(imageFileIDs[index_Texture]));

		gl.glGenTextures(1, textures_indek, 0);
		gl.glBindTexture(GL10.GL_TEXTURE_2D, textures_indek[0]);

		/*gl.glTexParameterf(GL10.GL_TEXTURE_2D, GL10.GL_TEXTURE_MIN_FILTER,
				GL10.GL_LINEAR);
		gl.glTexParameterf(GL10.GL_TEXTURE_2D, GL10.GL_TEXTURE_MAG_FILTER,
				GL10.GL_LINEAR);
				*/
		
		//gl.glTexParameterf(GL10.GL_TEXTURE_2D, GL10.GL_TEXTURE_WRAP_S, GL10.GL_CLAMP_TO_EDGE );
		//gl.glTexParameterf(GL10.GL_TEXTURE_2D, GL10.GL_TEXTURE_WRAP_T, GL10.GL_CLAMP_TO_EDGE );
		
		gl.glTexParameterf(GLES20.GL_TEXTURE_2D, GLES20.GL_TEXTURE_MAG_FILTER, GLES20.GL_NEAREST);
		gl.glTexParameterf(GLES20.GL_TEXTURE_2D, GLES20.GL_TEXTURE_MIN_FILTER, GLES20.GL_NEAREST);
		
		//gl.glTexParameterf(GLES20.GL_TEXTURE_2D, GLES20.GL_TEXTURE_WRAP_S, GLES20.GL_REPEAT );
		//gl.glTexParameterf(GLES20.GL_TEXTURE_2D, GLES20.GL_TEXTURE_WRAP_T, GLES20.GL_REPEAT );
		
		///gl.glTexParameterf(GL10.GL_TEXTURE_2D, GL10.GL_TEXTURE_MIN_FILTER,
				//GL10.GL_NEAREST);
		//gl.glTexParameterf(GL10.GL_TEXTURE_2D, GL10.GL_TEXTURE_MAG_FILTER,
				//GL10.GL_LINEAR);
		
		GLUtils.texImage2D(GLES20.GL_TEXTURE_2D, 0, bitmap, 0);

		bitmap.recycle();
	}
	
	public void loadBallTexture2(GL10 gl, Context context,int index_Texture) {
		// Bitmap bitmap = BitmapFactory.decodeResource(context.getResources(),
		// resource);

		/*Bitmap bitmap = BitmapFactory.decodeStream(context.getResources()
				.openRawResource(R.drawable.nature));*/
		
		Bitmap bitmap = BitmapFactory.decodeStream(context.getResources()
				.openRawResource(imageFileIDs2[index_Texture]));

		gl.glGenTextures(1, textures_indek, 0);
		gl.glBindTexture(GL10.GL_TEXTURE_2D, textures_indek[0]);

		/*gl.glTexParameterf(GL10.GL_TEXTURE_2D, GL10.GL_TEXTURE_MIN_FILTER,
				GL10.GL_LINEAR);
		gl.glTexParameterf(GL10.GL_TEXTURE_2D, GL10.GL_TEXTURE_MAG_FILTER,
				GL10.GL_LINEAR);
				*/
		
		//gl.glTexParameterf(GL10.GL_TEXTURE_2D, GL10.GL_TEXTURE_WRAP_S, GL10.GL_CLAMP_TO_EDGE );
		//gl.glTexParameterf(GL10.GL_TEXTURE_2D, GL10.GL_TEXTURE_WRAP_T, GL10.GL_CLAMP_TO_EDGE );
		
		gl.glTexParameterf(GLES20.GL_TEXTURE_2D, GLES20.GL_TEXTURE_MAG_FILTER, GLES20.GL_NEAREST);
		gl.glTexParameterf(GLES20.GL_TEXTURE_2D, GLES20.GL_TEXTURE_MIN_FILTER, GLES20.GL_NEAREST);
		
		//gl.glTexParameterf(GLES20.GL_TEXTURE_2D, GLES20.GL_TEXTURE_WRAP_S, GLES20.GL_REPEAT );
		//gl.glTexParameterf(GLES20.GL_TEXTURE_2D, GLES20.GL_TEXTURE_WRAP_T, GLES20.GL_REPEAT );
		
		///gl.glTexParameterf(GL10.GL_TEXTURE_2D, GL10.GL_TEXTURE_MIN_FILTER,
				//GL10.GL_NEAREST);
		//gl.glTexParameterf(GL10.GL_TEXTURE_2D, GL10.GL_TEXTURE_MAG_FILTER,
				//GL10.GL_LINEAR);
		
		GLUtils.texImage2D(GLES20.GL_TEXTURE_2D, 0, bitmap, 0);

		bitmap.recycle();
	}
	
	public void loadButtonTexture2(GL10 gl, Context context,int index_Texture) {
		// Bitmap bitmap = BitmapFactory.decodeResource(context.getResources(),
		// resource);

		/*Bitmap bitmap = BitmapFactory.decodeStream(context.getResources()
				.openRawResource(R.drawable.nature));*/
		
		Bitmap bitmap = BitmapFactory.decodeStream(context.getResources()
				.openRawResource(buttonId[index_Texture]));

		gl.glGenTextures(1, textures_indek, 0);
		gl.glBindTexture(GL10.GL_TEXTURE_2D, textures_indek[0]);

		/*gl.glTexParameterf(GL10.GL_TEXTURE_2D, GL10.GL_TEXTURE_MIN_FILTER,
				GL10.GL_LINEAR);
		gl.glTexParameterf(GL10.GL_TEXTURE_2D, GL10.GL_TEXTURE_MAG_FILTER,
				GL10.GL_LINEAR);
				*/
		
		//gl.glTexParameterf(GL10.GL_TEXTURE_2D, GL10.GL_TEXTURE_WRAP_S, GL10.GL_CLAMP_TO_EDGE );
		//gl.glTexParameterf(GL10.GL_TEXTURE_2D, GL10.GL_TEXTURE_WRAP_T, GL10.GL_CLAMP_TO_EDGE );
		
		gl.glTexParameterf(GLES20.GL_TEXTURE_2D, GLES20.GL_TEXTURE_MAG_FILTER, GLES20.GL_NEAREST);
		gl.glTexParameterf(GLES20.GL_TEXTURE_2D, GLES20.GL_TEXTURE_MIN_FILTER, GLES20.GL_NEAREST);
		
		//gl.glTexParameterf(GLES20.GL_TEXTURE_2D, GLES20.GL_TEXTURE_WRAP_S, GLES20.GL_REPEAT );
		//gl.glTexParameterf(GLES20.GL_TEXTURE_2D, GLES20.GL_TEXTURE_WRAP_T, GLES20.GL_REPEAT );
		
		///gl.glTexParameterf(GL10.GL_TEXTURE_2D, GL10.GL_TEXTURE_MIN_FILTER,
				//GL10.GL_NEAREST);
		//gl.glTexParameterf(GL10.GL_TEXTURE_2D, GL10.GL_TEXTURE_MAG_FILTER,
				//GL10.GL_LINEAR);
		
		GLUtils.texImage2D(GLES20.GL_TEXTURE_2D, 0, bitmap, 0);

		bitmap.recycle();
	}

}
