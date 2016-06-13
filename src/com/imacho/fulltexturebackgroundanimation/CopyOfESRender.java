package com.imacho.fulltexturebackgroundanimation;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

import android.content.Context;
import android.opengl.GLSurfaceView.Renderer;

public class CopyOfESRender implements Renderer {

	private ESText glText;
	private PrimitivesObject primobbg;
	private PrimitivesObject primobflappy;
	private PrimitivesObject primobground;
	private PrimitivesObject primob1;
	private PrimitivesObject primob3;
	private ObjectBall objectball2;
	//private ObjectBall objectball3;
	private ObjectBall objectballstick;
	//private ObjectBall objectballstickdua;
	private ObjectBall objectballbgstick;
	private PrimitivesObject primob;
	//private PrimitivesObject primob2;
	private TransObject transobject;
	private final MySphere mEarth,mEarth2,mEarth3;
	
	Context context;
	int Run_Mode=0;
	float CurrentAngle = 0.0f;			// Angle in degrees
	float CurrentAngle2 = 0.0f;			// Angle in degrees
	float AnimateStep = 2.0f;			// Rotation step per update
	float AnimateStep2 = 0.2f;			// Rotation step per update
	float ScaleStep = 1.0f;
	
	private float move_left_right1 =0.f;
	private float move_left_right2 =0.f;
	
	private float kecepatan_ground = 5.0f;
	private int faktor_naik_turun=0;
	private int flag_naik=1;
	
	private long startTime;
	private long fpsStartTime;
	private long numFrames;
	private float fps_temp;
	private long fpsElapsed_temp;
	private long numFrames_temp;
	private boolean startTimeInit=true;
	

	private long fpsStartTime2;
	private long numFrames2;
	private int numIDFrame=0;
	
	float CurrentScale =1.0f;

	float radius = 50.0f; // Ball's radius
	float x = radius; // Ball's center (x,y)
	float y = radius;
	float speedX = 0.5f; // Ball's speed (x,y)
	float speedY = 0.3f;

	int xMin, xMax, yMin, yMax;

	private int mywidth = 0;
	private int myheight = 0;
	private int jumlah_pantulan=0;
	
	private float x_player = 0;
	private float y_player = 0;
	private float x_player_end = 0;
	private float y_player_end = 0;
	private int Navigate = 0;
	private int positionRow = 0;
	private int positionCol = 0;
	//private int hori[][] = new int[2*primob.numberRow*primob.numberCol][2*primob.numberRow*primob.numberCol];
	//private int verti[][] = new int[2*primob.numberRow*primob.numberCol][2*primob.numberRow*primob.numberCol];
	private int hori[][] = new int[1000][1000];
	private int verti[][] = new int[1000][1000];
	private int numberCol =0;
	private int numberRow =0;
	private float WidthObject =0;
	
	private float x_anim_stick = 0;
	private float y_anim_stick = 0;
	
	private float x_touch = 0;
	private float y_touch = 0;
	
	private float x_pointer = 0;
	private float y_pointer = 0;
	
	private float x_pointer_awal = 0;
	private float y_pointer_awal = 0;
	
	private float x_lebar_layar = 0;
	private float y_tinggi_layar = 0;
	
	private float sudut_pointer = 0;
	
	private float jarak_center = 0;
	
	private String arah_joystick="";

	boolean get_size_screen=true;
	
	private float x_graf = 0;
	private float y_graf = 0;

	int[] textures_indek = new int[1];
	

	float black[] = new float[] { 0.0f, 0.0f, 0.0f, 1.0f };
	float yellow[] = new float[] { 1.0f, 1.0f, 0.0f, 1.0f  };
	float cyan[] = new float[] { 0.0f, 1.0f, 1.0f, 1.0f  };
	float white[] = new float[] { 1.0f, 1.0f, 1.0f, 1.0f  };
	float diffuseMaterial[] = new float[] { 0.73f, 0.13f, 0.86f, 1.0f }; // set cahaya warna ungu
	float diffuseMaterial2[] = new float[] { 0.5f, 0.5f, 0.5f, 1.0f }; // set cahaya warna ungu
	float lightAmbient[] = new float[] { 0.2f, 0.3f, 0.6f, 1.0f };
	
	float mat_specular[] = { 1.0f, 1.0f, 1.0f, 1.0f };
	float light_position[] = { 0.0f, 0.0f, 1.0f, 0.0f };
	float light_position2[] = { 0.0f, 0.0f, 1.0f, 0.0f };

	/** Constructor to set the handed over context */
	public CopyOfESRender(Context context) {
		// super();
		this.context = context;
		this.primobbg = new PrimitivesObject();
		this.primobflappy = new PrimitivesObject();
		this.primobground = new PrimitivesObject();
		this.primob1 = new PrimitivesObject();
		this.primob3 = new PrimitivesObject();
		this.objectball2 = new ObjectBall();
		//this.objectball3 = new ObjectBall();
		this.objectballstick = new ObjectBall();
		this.objectballbgstick = new ObjectBall();
		this.primob = new PrimitivesObject();
		//this.primob2 = new PrimitivesObject();
		this.mEarth = new MySphere(5, 3);
		this.mEarth2 = new MySphere(5, 3);
		this.mEarth3 = new MySphere(5, 3);
		this.transobject = new TransObject();
		
		//mengambil nilai xyx (posisi awal) labirin player
		this.x_player = primob.x_player;
		this.y_player = primob.y_player;
		this.x_player_end = primob.x_player_end;
		this.y_player_end = primob.y_player_end;
		this.positionRow = primob.positionRow;
		this.positionCol = primob.positionCol;
		this.hori = primob.hori;
		this.verti = primob.verti;
		this.numberCol = primob.numberCol;
		this.numberRow = primob.numberRow;
		this.WidthObject = primob.WidthObject;
		

	}
	

	@Override
	public void onDrawFrame(GL10 gl) {
		
		// menangkap ukuran layar
		if(get_size_screen){
			this.x_lebar_layar = mywidth;
			this.y_tinggi_layar = myheight;
			get_size_screen=false;
		}

		// Draw background color
		gl.glClear(GL10.GL_COLOR_BUFFER_BIT | GL10.GL_DEPTH_BUFFER_BIT);
		// gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
		gl.glEnable(GL10.GL_DEPTH_TEST);
		gl.glEnable(GL10.GL_NORMALIZE);
		
		//gl.glLoadIdentity();
				
		
		//gl.glMaterialfv(GL10.GL_FRONT, GL10.GL_AMBIENT_AND_DIFFUSE, makeFloatBuffer(cyan));
		//gl.glMaterialfv(GL10.GL_FRONT, GL10.GL_SPECULAR, white,0);
		//gl.glMaterialfv(GL10.GL_FRONT, GL10.GL_SHININESS, white,0);
		//gl.glMaterialfv(GL10.GL_FRONT, GL10.GL_DIFFUSE, white,0);
		
		// multi light
		/*float light1_ambient[] = { 0.2f, 0.2f, 0.2f, 1.0f };
		float light1_diffuse[] = { 1.0f, 1.0f, 1.0f, 1.0f };
		float light1_specular[] = { 1.0f, 1.0f, 1.0f, 1.0f };
		float light1_position[] = { -2.0f, 2.0f, 1.0f, 1.0f };
		float spot_direction[] = { -1.0f, -1.0f, 0.0f };
		*/

		/*gl.glLightfv(GL10.GL_LIGHT1, GL10.GL_AMBIENT, light1_ambient,0);
		gl.glLightfv(GL10.GL_LIGHT1, GL10.GL_DIFFUSE, light1_diffuse,0);
		gl.glLightfv(GL10.GL_LIGHT1, GL10.GL_SPECULAR, light1_specular,0);
		gl.glLightfv(GL10.GL_LIGHT1, GL10.GL_POSITION, light1_position,0);
		gl.glLightf(GL10.GL_LIGHT1, GL10.GL_CONSTANT_ATTENUATION, 1.5f);
		gl.glLightf(GL10.GL_LIGHT1, GL10.GL_LINEAR_ATTENUATION, 0.5f);
		gl.glLightf(GL10.GL_LIGHT1, GL10.GL_QUADRATIC_ATTENUATION, 0.2f);

		gl.glLightf(GL10.GL_LIGHT1, GL10.GL_SPOT_CUTOFF, 45.0f);
		gl.glLightfv(GL10.GL_LIGHT1, GL10.GL_SPOT_DIRECTION, spot_direction,0);
		gl.glLightf(GL10.GL_LIGHT1, GL10.GL_SPOT_EXPONENT, 2.0f);

		gl.glEnable(GL10.GL_LIGHT1);*/
		
		//end multi light

		//
		//membuat background
		gl.glPushMatrix(); 
			gl.glDisable(GL10.GL_LIGHTING);
				gl.glScalef(x_lebar_layar, y_tinggi_layar, 1.0f);
			    primobbg.draw_background(gl);
		    gl.glEnable(GL10.GL_LIGHTING);
	    gl.glPopMatrix();
	    
	  
	  int x_lebar_layar_temp=(int) (Math.ceil(x_lebar_layar/kecepatan_ground)*kecepatan_ground);
	    
	  //membuat ground 1 flappy
	    gl.glPushMatrix(); 
			gl.glDisable(GL10.GL_LIGHTING);
				gl.glTranslatef(move_left_right1, 0.f, 0.f);
				gl.glTranslatef(0, -50.f, 0.f);
				gl.glScalef(x_lebar_layar_temp, 1.5f*y_tinggi_layar/10, 1.0f);
				primobground.draw_background(gl);
		    gl.glEnable(GL10.GL_LIGHTING);   
	    gl.glPopMatrix();
	    
	    move_left_right1-=kecepatan_ground;
	    
	    if(-move_left_right1==x_lebar_layar_temp){
	    	move_left_right1=x_lebar_layar_temp;
	    }
	    
	  //membuat ground 2 flappy
	    gl.glPushMatrix(); 
		gl.glDisable(GL10.GL_LIGHTING);
			gl.glTranslatef(move_left_right2, 0.f, 0.f);
			gl.glTranslatef(x_lebar_layar_temp, 0.f, 0.f);
			gl.glTranslatef(0, -50.f, 0.f);
			gl.glScalef(x_lebar_layar_temp, 1.5f*y_tinggi_layar/10, 1.0f);
			primobground.draw_background(gl);
	    gl.glEnable(GL10.GL_LIGHTING);   
	    gl.glPopMatrix();
    
	    move_left_right2-=kecepatan_ground;
	    
	    if(-move_left_right2==2*x_lebar_layar_temp){
	    	move_left_right2=0;
	    }
	    
	   
	    
	  //membuat flappy bird
	 // Keep track of number of frames drawn		      
    	numFrames2++;
    	long fpsElapsed2 = System.currentTimeMillis() - fpsStartTime2;
    	if (fpsElapsed2 > 0.09 * 1000) { // every 5 seconds
    		float fps2 = (numFrames2 * 1000.0F) / fpsElapsed2;
    		//glText.draw( "Frames per second : "+ fps + " (" + numFrames + " frames in " + fpsElapsed + " ms)",150, 30);          // Draw Test String
    		fpsStartTime2 = System.currentTimeMillis();
    		numFrames2 = 0;    		
    		
    		gl.glPushMatrix(); 
  				gl.glDisable(GL10.GL_LIGHTING);
  				gl.glBlendFunc(GL10.GL_SRC_ALPHA, GL10.GL_ONE_MINUS_SRC_ALPHA);
  				gl.glEnable(GL10.GL_BLEND);
  				
  				// mengatur naik turun
  				gl.glTranslatef(0, faktor_naik_turun, 0.0f);
  				
  				gl.glTranslatef(x_lebar_layar/2, y_tinggi_layar/2, 0.0f);
  				gl.glScalef(x_lebar_layar/10, y_tinggi_layar/10, 1.0f);
  				primobflappy.loadBallTexture2(gl, context, numIDFrame);
  				//gl.glColor4f(1.0f, 1.0f, 1.0f, 1.0f);
  				primobflappy.draw_background(gl);
  				
  				gl.glDisable(GL10.GL_BLEND);
  		    	gl.glEnable(GL10.GL_LIGHTING);
  			gl.glPopMatrix();
  			
  			numIDFrame++;
  			
  			if(flag_naik==1){
  				faktor_naik_turun+=2;
  			}else if(flag_naik==-1){
  				faktor_naik_turun-=2;
  			}
  			
  			if(faktor_naik_turun%20==0){
  				flag_naik*=-1;
  			}
  			
  			if(numIDFrame%8==0){
  				numIDFrame=0;  				
  			}
    	 }else{
    		 gl.glPushMatrix(); 
				gl.glDisable(GL10.GL_LIGHTING);
				gl.glBlendFunc(GL10.GL_SRC_ALPHA, GL10.GL_ONE_MINUS_SRC_ALPHA);
				gl.glEnable(GL10.GL_BLEND);
				
				// mengatur naik turun
				gl.glTranslatef(0, faktor_naik_turun, 0.0f);
				
				gl.glTranslatef(x_lebar_layar/2, y_tinggi_layar/2, 0.0f);
				gl.glScalef(x_lebar_layar/10, y_tinggi_layar/10, 1.0f);
				primobflappy.loadBallTexture2(gl, context, numIDFrame);
				//gl.glColor4f(1.0f, 1.0f, 1.0f, 1.0f);
				primobflappy.draw_background(gl);
				
				gl.glDisable(GL10.GL_BLEND);
		    	gl.glEnable(GL10.GL_LIGHTING);
			gl.glPopMatrix();
    	 }
    	
  		
		
	  
		
		//fractal mandelbrot
		/*gl.glPushMatrix(); 
			gl.glDisable (GL10.GL_LIGHTING);
				gl.glTranslatef(550.0f, 200.0f, 0.0f);
				//gl.glScalef(CurrentScale*5.0f, CurrentScale*5.0f, 1.0f);
				gl.glScalef(60.0f, 60.0f, 1.0f);
			    //gl.glRotatef(CurrentAngle2, 0, 0, 1);	
			   // primob2.draw_fractal_mandelbrot(gl);
		    gl.glEnable (GL10.GL_LIGHTING);
	    gl.glPopMatrix();*/
	    
	  //fractal julia
  		/*gl.glPushMatrix(); 
  			gl.glDisable (GL10.GL_LIGHTING);
  				gl.glTranslatef(550.0f, 200.0f, 0.0f);
  				//gl.glScalef(CurrentScale*5.0f, CurrentScale*5.0f, 1.0f);
  				gl.glScalef(100.0f, 100.0f, 1.0f);
  			    gl.glRotatef(CurrentAngle2, 0, 0, 1);	
  			    //primob2.draw_fractal_julia(gl);
  		    gl.glEnable (GL10.GL_LIGHTING);
  	    gl.glPopMatrix();*/
  	    
  	//fractal julia_m
	/*gl.glPushMatrix(); 
		gl.glDisable(GL10.GL_LIGHTING);
			gl.glTranslatef(550.0f, 200.0f, 0.0f);
			//gl.glScalef(CurrentScale*5.0f, CurrentScale*5.0f, 1.0f);
			gl.glScalef(50.0f, 50.0f, 1.0f);
		    //gl.glRotatef(CurrentAngle2, 0, 0, 1);	
			gl.glEnable(GL10.GL_POINT_SMOOTH);
			gl.glPointSize(1);
		    primob2.draw_fractal_julia_m(gl);
	    gl.glEnable(GL10.GL_LIGHTING);
    gl.glPopMatrix();
	*/
	   
		
		// bola 1
		/*gl.glPushMatrix(); 
			gl.glDisable (GL10.GL_LIGHTING);
				gl.glTranslatef(400.0f, 200.0f, 0.0f);
				gl.glScalef(25.0f, 25.0f, 25.0f);
			    gl.glRotatef(CurrentAngle, 0, 1, 0);	
			    mEarth.draw(gl);
		    gl.glEnable (GL10.GL_LIGHTING);
	    gl.glPopMatrix();*/
	    
	    
	 // menampilkan object bola2 + lighting
	    /*gl.glPushMatrix ();
	    	gl.glTranslatef(600.0f, 200.0f, 0.0f);
	    	gl.glScalef(25.0f, 25.0f, 25.0f);
	   		gl.glTranslatef (0.0f, 0.0f, -5.0f);
	   		
	        gl.glPushMatrix ();
			   gl.glRotatef (CurrentAngle, 1.0f, 0.0f, 0.0f);
			   gl.glMaterialf(GL10.GL_FRONT, GL10.GL_SHININESS, 100.0f);			   
			   gl.glLightfv(GL10.GL_LIGHT0, GL10.GL_POSITION, light_position,0);
			   gl.glLightfv (GL10.GL_LIGHT0, GL10.GL_DIFFUSE,  white,0);
			   gl.glLightfv(GL10.GL_LIGHT0, GL10.GL_AMBIENT, lightAmbient, 0);
			   gl.glLightfv(GL10.GL_LIGHT0, GL10.GL_SPECULAR, white, 0);
	
			   gl.glTranslatef (0.0f, 0.0f, 3.5f);
			   gl.glDisable (GL10.GL_LIGHTING);			   		
			   		gl.glScalef(0.3f, 0.3f, 0.3f);
			   		transobject.draw_kubus(gl);
			   gl.glEnable (GL10.GL_LIGHTING);
		   gl.glPopMatrix ();
		   gl.glRotatef(CurrentAngle, 0, 1, 0);	
		   mEarth2.draw(gl);
		gl.glPopMatrix ();
		*/
		
		// menampilkan object bola3 + lighting
	    /*gl.glPushMatrix ();
	    	gl.glTranslatef(mywidth-2*radius, myheight-4f*radius, 0.0f);
	    	gl.glScalef(25.0f, 25.0f, 25.0f);
	   		gl.glTranslatef (0.0f, 0.0f, -5.0f);

	        gl.glPushMatrix ();
			   gl.glRotatef (CurrentAngle, 0.0f, 1.0f, 0.0f);
			   gl.glTranslatef (0.0f, 0.0f, 3.5f);
			  
			   gl.glMaterialf(GL10.GL_FRONT, GL10.GL_SHININESS, 100.0f);
			   gl.glLightfv (GL10.GL_LIGHT0, GL10.GL_POSITION, light_position,0);
			   gl.glLightfv(GL10.GL_LIGHT0, GL10.GL_AMBIENT, lightAmbient, 0);
			   gl.glLightfv(GL10.GL_LIGHT0, GL10.GL_DIFFUSE, white, 0);
			   gl.glLightfv(GL10.GL_LIGHT0, GL10.GL_SPECULAR, white, 0);
			   
			   gl.glDisable (GL10.GL_LIGHTING);			   		
			   		gl.glScalef(0.3f, 0.3f, 0.3f);
			   		transobject.draw_kubus(gl);
			   gl.glEnable (GL10.GL_LIGHTING);
			   
		   gl.glPopMatrix ();
		   
		   gl.glPushMatrix ();
			   gl.glRotatef (-CurrentAngle, 0.0f, 1.0f, 0.0f);
			   gl.glTranslatef (0.0f, 0.0f, 3.5f);
			  
			   gl.glMaterialf(GL10.GL_FRONT, GL10.GL_SHININESS, 100.0f);
			   gl.glLightfv (GL10.GL_LIGHT1, GL10.GL_POSITION, light_position,0);		   
			   gl.glLightfv(GL10.GL_LIGHT1, GL10.GL_AMBIENT, lightAmbient, 0);
			   gl.glLightfv(GL10.GL_LIGHT1, GL10.GL_DIFFUSE, white, 0);
			   gl.glLightfv(GL10.GL_LIGHT1, GL10.GL_SPECULAR, white, 0);
			   
			   gl.glDisable (GL10.GL_LIGHTING);			   		
			   		gl.glScalef(0.3f, 0.3f, 0.3f);
			   		transobject.draw_kubus(gl);
			   gl.glEnable (GL10.GL_LIGHTING);
			   gl.glEnable(GL10.GL_LIGHT0);
			   gl.glEnable(GL10.GL_LIGHT1);
			   
		   gl.glPopMatrix ();
		   
		   gl.glRotatef(CurrentAngle, 1, 0, 0);	
		   mEarth3.draw(gl);
		gl.glPopMatrix ();
		
		gl.glLoadIdentity();
		*/
		
		// cahaya bola ungu
		/*gl.glPushMatrix ();
			gl.glTranslatef(mywidth-2*radius, myheight-1.2f*radius, 0.0f);
	    	gl.glScalef(15.0f, 15.0f, 15.0f);
	   		gl.glTranslatef (0.0f, 0.0f, -5.0f);   		
	   		
			   gl.glPushMatrix ();
				   gl.glRotatef (CurrentAngle, 1.0f, 0.0f, 0.0f);
				   gl.glMaterialf(GL10.GL_FRONT, GL10.GL_SHININESS, 100.0f);
				   
				   gl.glLightfv(GL10.GL_LIGHT0, GL10.GL_POSITION, light_position,0);
				   gl.glLightfv (GL10.GL_LIGHT0, GL10.GL_DIFFUSE,  diffuseMaterial,0);
				   gl.glLightfv(GL10.GL_LIGHT0, GL10.GL_AMBIENT, lightAmbient, 0);
				   gl.glLightfv(GL10.GL_LIGHT0, GL10.GL_SPECULAR, white, 0);
				   
				   gl.glTranslatef (0.0f, 0.0f, 3.5f);
				   gl.glDisable (GL10.GL_LIGHTING);
					   gl.glScalef(0.3f, 0.3f, 0.3f);
				   	   transobject.draw_kubus(gl);
			   	   gl.glEnable (GL10.GL_LIGHTING);
			   	   gl.glEnable(GL10.GL_LIGHT0);
				   gl.glDisable(GL10.GL_LIGHT1);
			   gl.glPopMatrix ();
			mEarth2.draw(gl);
	   gl.glPopMatrix ();
	   */
	   
		// menampilkan persegi dengan gradasi warna
		/*gl.glPushMatrix();

		gl.glTranslatef(0.0f, 1.0f, 0.0f);
		gl.glTranslatef(0.0f, 0.0f, -5.0f);
		// objectarena.draw_kotak(gl);
		gl.glPopMatrix();*/

		// menampilkan lingkaran dengan tepian berwarna
		/*gl.glPushMatrix();
			gl.glDisable (GL10.GL_LIGHTING);
				// gl.glScalef(150.0f, 150.0f, 150.0f);
				// gl.glTranslatef(150.0f, 150.0f, -5.0f);
				gl.glLineWidth(1.0f);
				gl.glEnable(GL10.GL_LINE_SMOOTH);
				objectball.draw_circle(gl);
			gl.glEnable (GL10.GL_LIGHTING);
		gl.glPopMatrix();
		*/
		
		// menampilkan bola bumi
		/*gl.glPushMatrix();
			gl.glDisable (GL10.GL_LIGHTING);
				//gl.glLineWidth(1.0f);
				//gl.glEnable(GL10.GL_LINE_SMOOTH);
				// bounds.set(x - radius, y - radius, x + radius, y + radius);
				gl.glTranslatef(-x, -y, 0.0f);
				gl.glTranslatef(mywidth-radius, myheight-radius, 0.0f);
				gl.glScalef(50.0f/3.0f,50.0f/3.0f,50.0f/3.0f);	
				gl.glRotatef(CurrentAngle, 0, 1, 0);	
				mEarth.draw(gl);	
			gl.glEnable (GL10.GL_LIGHTING);
		gl.glPopMatrix();
		*/

		// menampilkan bola putar dengan gradasi warna (3D)
		/*gl.glPushMatrix();
			gl.glDisable (GL10.GL_LIGHTING);
				gl.glLineWidth(1.0f);
				gl.glEnable(GL10.GL_LINE_SMOOTH);
				// bounds.set(x - radius, y - radius, x + radius, y + radius);
				//gl.glTranslatef(x, y, 0.0f);
				gl.glTranslatef(50.0f, 50.0f, 0.0f);
				gl.glScalef(50.0f/3.0f,50.0f/3.0f,50.0f/3.0f);				
				//objectball2.draw_circle_color(gl);
				gl.glRotatef(CurrentAngle, 0, 1, 0);	
				//mEarth3.draw(gl);
			gl.glEnable (GL10.GL_LIGHTING);
		gl.glPopMatrix();
		*/
		
		// menampilkan bola pantul dengan gradasi warna (3D)
		gl.glPushMatrix();
		gl.glDisable (GL10.GL_LIGHTING);
			gl.glLineWidth(1.0f);
			gl.glEnable(GL10.GL_LINE_SMOOTH);
			// bounds.set(x - radius, y - radius, x + radius, y + radius);
			gl.glTranslatef(x, y, 0.0f);
			gl.glTranslatef(50.0f, 50.0f, 0.0f);
			gl.glScalef(50.0f/3.0f,50.0f/3.0f,50.0f/3.0f);				
			//objectball2.draw_circle_color(gl);
			gl.glRotatef(CurrentAngle, 0, 1, 0);	
			mEarth3.draw(gl);
		gl.glEnable (GL10.GL_LIGHTING);
	gl.glPopMatrix();
		
		
		// segitiga merah
		/*gl.glPushMatrix(); 
			gl.glDisable (GL10.GL_LIGHTING);
				//GLU.gluLookAt(gl, 0, 0, 0.01f, 0f, 0f, 0f, 0f, 1.0f, 0.0f);
				gl.glColor4f(1.0f, 0.0f, 0.0f, 0.5f);
				gl.glTranslatef(50.0f, 60.0f, 0.0f);
				gl.glScalef(80.0f, 80.0f, 0.0f);			
				gl.glRotatef(180, 1.0f, 0.0f, 0.0f);
				//gl.glRotatef(mAngle, 0.0f, 1.0f, 0.0f);
				gl.glTranslatef(-0.5f, -0.5f, 0.0f);
				objectball.draw_segitiga(gl);	
			gl.glEnable (GL10.GL_LIGHTING);
		gl.glPopMatrix();
		*/
		
		
		// segitiga hijau
		/*gl.glPushMatrix(); 
			gl.glDisable (GL10.GL_LIGHTING);
				//GLU.gluLookAt(gl, 0, 0, 0.01f, 0f, 0f, 0f, 0f, 1.0f, 0.0f);
				gl.glColor4f(0.0f, 0.0f, 1.0f, 0.5f);
				gl.glTranslatef(60.0f, 60.0f, 0.0f);
				gl.glScalef(80.0f, 80.0f, 0.0f);			
				gl.glRotatef(180, 1.0f, 0.0f, 0.0f);
				//gl.glRotatef(mAngle, 0.0f, 1.0f, 0.0f);
				gl.glTranslatef(-0.5f, -0.5f, 0.0f);
				objectball.draw_segitiga(gl);
			gl.glEnable (GL10.GL_LIGHTING);
		gl.glPopMatrix();
		*/

		// menampilkan lingkaran dengan warna merah + blending
		/*gl.glPushMatrix();		
			gl.glDisable (GL10.GL_LIGHTING);	
				gl.glEnable(GL10.GL_BLEND); // Turn blending on (NEW)
				gl.glDisable(GL10.GL_DEPTH_TEST); // Turn depth testing off (NEW)
				gl.glBlendFunc(GL10.GL_SRC_ALPHA, GL10.GL_ONE_MINUS_SRC_ALPHA);
					gl.glLineWidth(1.0f);
					gl.glEnable(GL10.GL_LINE_SMOOTH);
					gl.glTranslatef(100, 150, 0.0f);
					
					objectball3.draw_circle_color(gl,1.0f,0.f,0.f,0.5f);
				gl.glDisable( GL10.GL_BLEND );                  // Disable Alpha Blend
			gl.glEnable (GL10.GL_LIGHTING);			
		gl.glPopMatrix();
		
		// menampilkan lingkaran dengan warna hijau + blending
		gl.glPushMatrix();		
			gl.glDisable (GL10.GL_LIGHTING);	
				gl.glEnable(GL10.GL_BLEND); // Turn blending on (NEW)
				gl.glDisable(GL10.GL_DEPTH_TEST); // Turn depth testing off (NEW)
				gl.glBlendFunc(GL10.GL_SRC_ALPHA, GL10.GL_ONE_MINUS_SRC_ALPHA);
					gl.glLineWidth(1.0f);
					gl.glEnable(GL10.GL_LINE_SMOOTH);
					gl.glTranslatef(70,100, 0.0f);
					
					objectball3.draw_circle_color(gl,0.0f,1.f,0.f,0.5f);
				gl.glDisable( GL10.GL_BLEND );                  // Disable Alpha Blend
			gl.glEnable (GL10.GL_LIGHTING);			
		gl.glPopMatrix();
		
		
		// menampilkan lingkaran dengan warna biru + blending		
		gl.glPushMatrix();		
			gl.glDisable (GL10.GL_LIGHTING);	
				gl.glEnable(GL10.GL_BLEND); // Turn blending on (NEW)
				gl.glDisable(GL10.GL_DEPTH_TEST); // Turn depth testing off (NEW)
				gl.glBlendFunc(GL10.GL_SRC_ALPHA, GL10.GL_ONE_MINUS_SRC_ALPHA);
					gl.glLineWidth(1.0f);
					gl.glEnable(GL10.GL_LINE_SMOOTH);
					gl.glTranslatef(130,100, 0.0f);
					
					objectball3.draw_circle_color(gl,0.0f,0.f,1.f,0.5f);
				gl.glDisable( GL10.GL_BLEND );                  // Disable Alpha Blend
			gl.glEnable (GL10.GL_LIGHTING);			
		gl.glPopMatrix();
		*/


		// update
		moveWithCollisionDetection(this);
		set((int)-radius, (int)-radius, mywidth, myheight);

		// Log.i("Test get width : ", "" + mywidth);
		// Log.i("Test get height : ", "" + myheight);

		// render text
		gl.glPushMatrix();
			gl.glDisable (GL10.GL_LIGHTING);
				// gl.glScalef(10.0f, 10.0f, 10.0f);
		
				// enable texture + alpha blending
				// NOTE: this is required for text rendering! we could incorporate it
				// into
				// the GLText class, but then it would be called multiple times (which
				// impacts performance).
				
				gl.glEnable(GL10.GL_TEXTURE_2D); // Enable Texture Mapping
				gl.glEnable(GL10.GL_BLEND); // Enable Alpha Blend
				gl.glDisable(GL10.GL_DEPTH_TEST); // Turn depth testing off (NEW)
				gl.glBlendFunc(GL10.GL_SRC_ALPHA, GL10.GL_ONE_MINUS_SRC_ALPHA); // Set
																				// Alpha
																				// Blend
																				// Function
				
				glText.begin(1.0f, 1.0f, 1.0f, 1.0f); // Begin Text Rendering (Set Color
				// WHITE)
				glText.draw("Jumlah Pantulan : (" + jumlah_pantulan + ")", 450, 2); // Draw
												// Test
												// String
				// textview.setText(String.valueOf(x));
				// glText.draw( "Line 1", -2, 2 ); // Draw Test String
				// glText.draw( "Line 2", 0, 0 ); // Draw Test String
				glText.end(); // End Text Rendering
				
				gl.glLoadIdentity();
				
				glText.begin(1.0f, 1.0f, 1.0f, 1.0f); // Begin Text Rendering (Set Color
														// WHITE)
				glText.draw("Posisi Bola : (" + x + "," + y + ")", 50, 2); // Draw
																				// Test
																				// String
				// textview.setText(String.valueOf(x));
				// glText.draw( "Line 1", -2, 2 ); // Draw Test String
				// glText.draw( "Line 2", 0, 0 ); // Draw Test String
				glText.end(); // End Text Rendering
				
				gl.glLoadIdentity();
				
				glText.begin(0.0f, 1.0f, 0.0f, 1.0f); // Begin Text Rendering (Set Color
														// WHITE)
				glText.draw("Posisi Touch : (" + x_touch + "," + y_touch + ")", 450, 400); // Draw
																				// Test
																				// String
				// textview.setText(String.valueOf(x));
				// glText.draw( "Line 1", -2, 2 ); // Draw Test String
				// glText.draw( "Line 2", 0, 0 ); // Draw Test String
				glText.end(); // End Text Rendering
				
				
				gl.glLoadIdentity();
				
				glText.begin(0.0f, 1.0f, 0.0f, 1.0f); // Begin Text Rendering (Set Color
														// WHITE)
				glText.draw("Posisi Player : (" + ((400*x_player)+50) + "," + ((400*y_player)+50) + ")", 50, 400); // Draw
																				// Test
																				// String
				// textview.setText(String.valueOf(x));
				// glText.draw( "Line 1", -2, 2 ); // Draw Test String
				// glText.draw( "Line 2", 0, 0 ); // Draw Test String
				glText.end(); // End Text Rendering
				
				gl.glLoadIdentity();
				
				glText.begin(0.0f, 1.0f, 0.0f, 1.0f); // Begin Text Rendering (Set Color
														// WHITE)
				glText.draw("Ukuran Layar : (" + x_lebar_layar + "," + y_tinggi_layar + ")", 50, 380); // Draw
																				// Test
																				// String
				// textview.setText(String.valueOf(x));
				// glText.draw( "Line 1", -2, 2 ); // Draw Test String
				// glText.draw( "Line 2", 0, 0 ); // Draw Test String
				glText.end(); // End Text Rendering
				
				gl.glLoadIdentity();
				
				glText.begin(0.0f, 1.0f, 0.0f, 1.0f); // Begin Text Rendering (Set Color
														// WHITE)
				glText.draw("Posisi Tujuan Player : (" + ((400*x_player_end)+50) + "," + ((400*y_player_end)+50)  + ")", 450, 380); // Draw
																				// Test
																				// String
				// textview.setText(String.valueOf(x));
				// glText.draw( "Line 1", -2, 2 ); // Draw Test String
				// glText.draw( "Line 2", 0, 0 ); // Draw Test String
				glText.end(); // End Text Rendering
				
				if( (x_player>=(x_player_end-(WidthObject/2))) && (x_player<=(x_player_end+(WidthObject/2)))){
					if( (y_player>=(y_player_end-(WidthObject/2))) && (y_player<=(y_player_end+(WidthObject/2)))){
					gl.glLoadIdentity();
					
					glText.begin(1.0f, 1.0f, 1.0f, 1.0f); // Begin Text Rendering (Set Color
															// WHITE)
					//gl.glTranslatef(((400*x_player_end)+50)+50, ((400*y_player_end)+50), 0.f);
					
					gl.glTranslatef(mywidth/2, -myheight/2, 0.f);
					gl.glScalef(2.5f, 2.5f, 1.0f);
					gl.glTranslatef(5.f, 140.f, 0.f);
					//gl.glRotatef(CurrentAngle, 0.1f, 1.0f, 0.2f); // Rotate
					gl.glTranslatef(-50.f, 0.f, 0.f);					
					glText.draw(" You Win..!", 0,0 ); // Draw
																					// Test
																					// String
					// textview.setText(String.valueOf(x));
					// glText.draw( "Line 1", -2, 2 ); // Draw Test String
					// glText.draw( "Line 2", 0, 0 ); // Draw Test String
					glText.end(); // End Text Rendering
					//flag_win=1;
					}
				}
				
				
				//gl.glLoadIdentity();
				//glText.begin(0.0f, 0.0f, 1.0f, 1.0f); // Begin Text Rendering (Set Color
				
				// Keep track of number of frames drawn		      
		    	//numFrames++;
		    	//long fpsElapsed = System.currentTimeMillis() - fpsStartTime;
		    	//float fps = (numFrames * 1000.0F) / fpsElapsed;	
		    	
		    	
		    	
		    	
		    	//if (fpsElapsed > 5 * 1000) { // every 5 seconds
		    		//float fps = (numFrames * 1000.0F) / fpsElapsed;		    		
		    	//	glText.draw( "Frames per second : "+ fps + " (" + numFrames + " frames in " + fpsElapsed + " ms)",470, 30);          // Draw Test String
		    		
		    		
		    		
		    		//temp nilai fps dan fpsElapsed terakhir
		    	//	fps_temp=fps;
		    	//	numFrames_temp=numFrames;
			    //	fpsElapsed_temp=fpsElapsed;
		    		
		    	//	fpsStartTime = System.currentTimeMillis();
		    	//	numFrames = 0;    	
		    	//	startTimeInit=false;
		    	/*}else{
		    		if(startTimeInit){
		    			//glText.draw( "Frames per second : "+ fps + " (" + numFrames + " frames in " + fpsElapsed + " ms)",450, 30);          // Draw Test String
		    			
		    		}else{
		    			glText.draw( "Frames per second : "+ fps_temp + " (" + numFrames_temp + " frames in " + fpsElapsed_temp + " ms)",470, 30);          // Draw Test String
		    		}
		    	}*/
		    	//glText.end(); // End Text Rendering
		    		
				gl.glLoadIdentity();
				
				glText.begin(0.0f, 1.0f, 0.0f, 1.0f); // Begin Text Rendering (Set Color
														// WHITE)
				glText.draw("Arah : (" + arah_joystick + ")", 450, 460); // Draw
																				// Test
																				// String
				// textview.setText(String.valueOf(x));
				// glText.draw( "Line 1", -2, 2 ); // Draw Test String
				// glText.draw( "Line 2", 0, 0 ); // Draw Test String
				glText.end(); // End Text Rendering
				
				gl.glLoadIdentity();
				
				glText.begin(0.0f, 1.0f, 0.0f, 1.0f); // Begin Text Rendering (Set Color
														// WHITE)
				glText.draw("Nilai Jarak ke Center : (" + jarak_center + ")", 450, 440); // Draw
																				// Test
																				// String
				// textview.setText(String.valueOf(x));
				// glText.draw( "Line 1", -2, 2 ); // Draw Test String
				// glText.draw( "Line 2", 0, 0 ); // Draw Test String
				glText.end(); // End Text Rendering
				
				gl.glLoadIdentity();
				
				glText.begin(.0f, 1.0f, 0.0f, 1.0f); // Begin Text Rendering (Set Color
														// WHITE)
				glText.draw("Posisi xy_anim_stick : (" + x_anim_stick + "," + y_anim_stick + ")", 450, 420); // Draw
																				// Test
																				// String
				// textview.setText(String.valueOf(x));
				// glText.draw( "Line 1", -2, 2 ); // Draw Test String
				// glText.draw( "Line 2", 0, 0 ); // Draw Test String
				glText.end(); // End Text Rendering
				
				gl.glLoadIdentity();
				
				glText.begin(0.0f, 1.0f, 0.0f, 1.0f); // Begin Text Rendering (Set Color
														// WHITE)
				glText.draw("Sudut Pointer : (" + sudut_pointer + ")", 50, 460); // Draw
																				// Test
																				// String
				// textview.setText(String.valueOf(x));
				// glText.draw( "Line 1", -2, 2 ); // Draw Test String
				// glText.draw( "Line 2", 0, 0 ); // Draw Test String
				glText.end(); // End Text Rendering
				
				gl.glLoadIdentity();
				
				glText.begin(0.0f, 1.0f, 0.0f, 1.0f); // Begin Text Rendering (Set Color
														// WHITE)
				glText.draw("Posisi xy_pointer : (" + x_pointer + "," + y_pointer + ")", 50, 440); // Draw
																				// Test
																				// String
				// textview.setText(String.valueOf(x));
				// glText.draw( "Line 1", -2, 2 ); // Draw Test String
				// glText.draw( "Line 2", 0, 0 ); // Draw Test String
				glText.end(); // End Text Rendering
				
				gl.glLoadIdentity();
				
				glText.begin(0.0f, 1.0f, 0.0f, 1.0f); // Begin Text Rendering (Set Color
														// WHITE)
				glText.draw("Posisi xy_pointer awal : (" + x_pointer_awal + "," + y_pointer_awal + ")", 50, 420); // Draw
																				// Test
																				// String
				// textview.setText(String.valueOf(x));
				// glText.draw( "Line 1", -2, 2 ); // Draw Test String
				// glText.draw( "Line 2", 0, 0 ); // Draw Test String
				glText.end(); // End Text Rendering
				
				/*gl.glLoadIdentity();
				
				glText.begin( 1.0f, 1.0f, 0.0f, 1.0f ); // Begin Text Rendering 
				gl.glScalef(0.5f, 0.5f, 0.5f);
				glText.draw( "Grafika Komputer", 40.0f, 140); //
				// Draw Test String
				glText.end(); // End Text Rendering
				*/
		
				gl.glLoadIdentity();
				
				glText.begin( 0.0f, 0.0f, 1.0f, 0.5f ); // Begin Text Rendering
				
					//gl.glTranslatef(x_graf, y_graf, 0.f);
					
					//Log.v("nilai x_graf :", ""+x_graf);
					//Log.v("nilai y_graf :", ""+y_graf);
					
					gl.glTranslatef(345.f, 200.f, 0.f);		
					//gl.glRotatef(-30, 0.0f, 0.0f, 1.0f);
					
					gl.glScalef(1.5f, 1.5f, 1.5f);
					gl.glTranslatef(5.f, 140.f, 0.f);
					gl.glRotatef(CurrentAngle, 0.1f, 1.0f, 0.2f); // Rotate
					gl.glTranslatef(-50.f, 0.f, 0.f);
					glText.draw( "Grafika Komputer", 0.0f, 0.0f); //
					// Draw Test String
				glText.end(); // End Text Rendering
			gl.glEnable (GL10.GL_LIGHTING);
			
			// disable texture + alpha
			gl.glDisable(GL10.GL_BLEND); // Disable Alpha Blend
			gl.glDisable(GL10.GL_TEXTURE_2D); // Disable Texture Mapping
		gl.glPopMatrix();

		// membuat background joystick dari lingkaran dengan texture
		gl.glPushMatrix();
			gl.glDisable (GL10.GL_LIGHTING);
				gl.glLineWidth(1.0f);
				gl.glEnable(GL10.GL_LINE_SMOOTH);
				//gl.glTranslatef(-x, -y, 0.0f);
				gl.glTranslatef(mywidth-(2*radius)-radius, 0.0f+radius, 0.0f);
				objectballbgstick.draw_circle_color(gl);	
			gl.glEnable (GL10.GL_LIGHTING);
		gl.glPopMatrix();
		
		// membuat joystick dari lingkaran dengan texture
		gl.glPushMatrix();
			gl.glDisable (GL10.GL_LIGHTING);
				gl.glLineWidth(1.0f);
				gl.glEnable(GL10.GL_LINE_SMOOTH);
				gl.glTranslatef(x_anim_stick, y_anim_stick, 0.0f);
				
				//gl.glTranslatef(mywidth-2*radius, 0.5f*radius, 0.0f);
				
				//canvas.drawBitmap(_joystick.get_joystick(),
				//_controls._touchingPoint.x - 26, _controls._touchingPoint.y - 26, null);
				
				gl.glTranslatef(mywidth-(1.5f*radius)-radius, (0.5f*radius)+radius, 0.0f);
				//gl.glTranslatef(mywidth-1.5f*radius, 0.5f*radius, 0.0f);
				//gl.glTranslatef(1013.0f, 500.0f, 0.0f);
				
				
				gl.glScalef(0.5f, 0.5f, 1.0f);
				objectballstick.draw_circle_color(gl);	
			gl.glEnable (GL10.GL_LIGHTING);
		gl.glPopMatrix();
		
		// membuat joystick dari lingkaran dengan texture 2
		/*gl.glPushMatrix();
			gl.glDisable (GL10.GL_LIGHTING);
				gl.glLineWidth(1.0f);
				gl.glEnable(GL10.GL_LINE_SMOOTH);
				gl.glTranslatef(x_anim_stick, y_anim_stick, 0.0f);
				
				//gl.glTranslatef(mywidth-2*radius, 0.5f*radius, 0.0f);
				
				gl.glTranslatef(-5*radius,0.0f,0.0f);
				//canvas.drawBitmap(_joystick.get_joystick(),
				//_controls._touchingPoint.x - 26, _controls._touchingPoint.y - 26, null);
				
				gl.glTranslatef(mywidth-(1.5f*radius)-radius, (0.5f*radius)+radius, 0.0f);
				//gl.glTranslatef(mywidth-1.5f*radius, 0.5f*radius, 0.0f);
				//gl.glTranslatef(1013.0f, 500.0f, 0.0f);
				
				
				gl.glScalef(0.5f, 0.5f, 1.0f);
				objectballstickdua.draw_circle_color(gl);	
			gl.glEnable (GL10.GL_LIGHTING);
			gl.glPopMatrix();*/
		//Log.v("mywidth: ", "" + mywidth);
		//Log.v("myheight: ", "" + myheight);
		//Log.v("radius: ", "" + radius);
		
		// membuat jaring-jaring labirin
		gl.glPushMatrix();
		   	 gl.glDisable (GL10.GL_LIGHTING);
		   	 //gl.glDisable(GL10.GL_DEPTH_TEST); // Turn depth testing off (NEW)
			   	//gl.glTranslatef(400.0f, 400.0f, 0.0f);
		   	    gl.glTranslatef(50.0f, 50.0f, 0.0f);
		   		//gl.glScalef(25.0f, 25.0f, 25.0f);
		   		gl.glScalef(400.0f, 400.0f, 1.0f);
		  		gl.glTranslatef (0.0f, 0.0f, -5.0f);
		  		gl.glLineWidth(4.0f);
				gl.glEnable(GL10.GL_LINE_SMOOTH);
			   	primob.draw_line_maze_horiverti(gl);
			 gl.glEnable (GL10.GL_LIGHTING);
		gl.glPopMatrix();
		
		//gl.glLoadIdentity();
		
		// segitiga dengan texture sebagai player labirin
		/*gl.glPushMatrix(); 
		 	gl.glDisable (GL10.GL_LIGHTING);
			//GLU.gluLookAt(gl, 0, 0, 0.01f, 0f, 0f, 0f, 0f, 1.0f, 0.0f);
			
			gl.glTranslatef(50.0f, 60.0f, 0.0f);
			gl.glScalef(80.0f, 80.0f, 0.0f);			
			gl.glRotatef(180, 1.0f, 0.0f, 0.0f);
			gl.glTranslatef(-0.5f, -0.5f, 0.0f);
			objectball.draw_segitiga_texture(gl);		
			gl.glEnable (GL10.GL_LIGHTING);
		gl.glPopMatrix();*/
		
		// membuat player untuk labirin
		gl.glPushMatrix();
			gl.glDisable (GL10.GL_LIGHTING);
				gl.glTranslatef(50.0f, 50.0f, 0.0f);
		   		gl.glScalef(400.0f, 400.0f, 1.0f);
		  		gl.glTranslatef (0.0f, 0.0f, -5.0f);
		  		
		  		// start me-manage pergerakan player
		  		if(Navigate == 1) //Navigate right
			     {
			          gl.glTranslatef(x_player, y_player, 0.0f);
			          gl.glRotatef(-90.0f, 0.0f, 0.0f, 1.0f);
			          gl.glTranslatef(-x_player, -y_player, 0.0f);
			     }
			     else if(Navigate == 2) //Navigate bottom
			     {
			          gl.glTranslatef(x_player, y_player, 0.0f);
			          gl.glRotatef(-180.0f, 0.0f, 0.0f, 1.0f);
			          gl.glTranslatef(-x_player, -y_player, 0.0f);     
			     }
			     else if(Navigate == 3) //Navigate left
			     {
			          gl.glTranslatef(x_player, y_player, 0.0f);
			          gl.glRotatef(90.0f, 0.0f, 0.0f, 1.0f);
			          gl.glTranslatef(-x_player, -y_player, 0.0f);     
			     }		  		
		  		// end me-manage pergerakan player
		  		
				primob.draw_segitiga_labirin_player(gl,positionCol,positionRow);
				//primob.draw_segitiga_labirin_player(gl);
		  		//primob1.draw_segitiga_texture(gl,positionCol,positionRow);
			gl.glEnable (GL10.GL_LIGHTING);
		gl.glPopMatrix();
		
		// membuat player untuk labirin as bot
		/*int min = 1;
		int max = 3;

		Random init_random = new Random();
		int my_random = init_random.nextInt(max - min + 1) + min;
		
		Navigate=my_random;
		Log.v("my_random =",""+my_random);
		*/
		
		
		// Update the rotational angle after each refresh     	
			// re-Calculate animation parameters
	        CurrentAngle += AnimateStep;
			if (CurrentAngle > 360.0) {
				//CurrentAngle -= 360.0*Math.floor(CurrentAngle/360.0);
				CurrentAngle=0.0f;
				CurrentAngle += AnimateStep;
			}
			
			CurrentAngle2 += AnimateStep2;
			if (CurrentAngle2 > 360.0) {
				//CurrentAngle -= 360.0*Math.floor(CurrentAngle/360.0);
				CurrentAngle2=0.0f;
				CurrentAngle2 += AnimateStep2;
			}
			
			// pengkondisian penghitungan pantulan mulai dari nol
			/*if(Run_ModeScale==0){
				this.CurrentScale-=ScaleStep;
				this.Run_ModeScale=1;
			}*/
			
			CurrentScale += ScaleStep;
			if (CurrentScale % 10.0f==0) {				
				ScaleStep = -1*ScaleStep;
			}
			
			
		//gl.glFlush();

	}
	
	public float getX_graf() {
		return x_graf;
	}


	public void setX_graf(float x_graf) {
		this.x_graf = x_graf;
	}


	public float getY_graf() {
		return y_graf;
	}


	public void setY_graf(float y_graf) {
		this.y_graf = y_graf;
	}


	public float getX_touch() {
		return x_touch;
	}


	public void setX_touch(float x_touch) {
		this.x_touch = x_touch;
	}


	public float getY_touch() {
		return y_touch;
	}


	public void setY_touch(float y_touch) {
		this.y_touch = y_touch;
	}
	
	public String getArah_joystick() {
		return arah_joystick;
	}


	public void setArah_joystick(String arah_joystick) {
		this.arah_joystick = arah_joystick;
	}
	
	public float getJarak_center() {
		return jarak_center;
	}


	public void setJarak_center(float jarak_center) {
		this.jarak_center = jarak_center;
	}

	
	public float getSudut_pointer() {
		return sudut_pointer;
	}


	public void setSudut_pointer(float sudut_pointer) {
		this.sudut_pointer = sudut_pointer;
	}
	
	public float getX_lebar_layar() {
		return x_lebar_layar;
	}


	public void setX_lebar_layar(float x_lebar_layar) {
		this.x_lebar_layar = x_lebar_layar;
	}


	public float getY_tinggi_layar() {
		return y_tinggi_layar;
	}


	public void setY_tinggi_layar(float y_tinggi_layar) {
		this.y_tinggi_layar = y_tinggi_layar;
	}
	
	public float getX_pointer_awal() {
		return x_pointer_awal;
	}


	public void setX_pointer_awal(float x_pointer_awal) {
		this.x_pointer_awal = x_pointer_awal;
	}


	public float getY_pointer_awal() {
		return y_pointer_awal;
	}


	public void setY_pointer_awal(float y_pointer_awal) {
		this.y_pointer_awal = y_pointer_awal;
	}
	
	public float getX_pointer() {
		return x_pointer;
	}


	public void setX_pointer(float x_pointer) {
		this.x_pointer = x_pointer;
	}


	public float getY_pointer() {
		return y_pointer;
	}


	public void setY_pointer(float y_pointer) {
		this.y_pointer = y_pointer;
	}
	
	public float getX_anim_stick() {
		return x_anim_stick;
	}


	public void setX_anim_stick(float x_anim_stick) {
		this.x_anim_stick = x_anim_stick;
	}


	public float getY_anim_stick() {
		return y_anim_stick;
	}


	public void setY_anim_stick(float y_anim_stick) {
		this.y_anim_stick = y_anim_stick;
	}
	
	public float getRadius() {
		return radius;
	}


	public void setRadius(float radius) {
		this.radius = radius;
	}


	public int getMywidth() {
		return mywidth;
	}


	public void setMywidth(int mywidth) {
		this.mywidth = mywidth;
	}


	public int getMyheight() {
		return myheight;
	}


	public void setMyheight(int myheight) {
		this.myheight = myheight;
	}


	public int getNumberRow() {
		return numberRow;
	}

	public void setNumberRow(int numberRow) {
		this.numberRow = numberRow;
	}
	
	public float getWidthObject() {
		return WidthObject;
	}

	public void setWidthObject(float widthObject) {
		WidthObject = widthObject;
	}

	public float getX_player() {
		return x_player;
	}

	public void setX_player(float x_player) {
		this.x_player = x_player;
	}

	public float getY_player() {
		return y_player;
	}

	public void setY_player(float y_player) {
		this.y_player = y_player;
	}

	public int getNavigate() {
		return Navigate;
	}

	public void setNavigate(int navigate) {
		Navigate = navigate;
	}
	
	public boolean goForward()
	{
	     if(Navigate == 0 && positionRow > 0 && hori[positionRow][positionCol] == 0) return true;
	     if(Navigate == 1 && positionCol < (numberCol-1) && verti[positionRow][positionCol+1] == 0) return true;
	     if(Navigate == 2 && positionRow < (numberRow-1) && hori[positionRow+1][positionCol] == 0) return true;
	     if(Navigate == 3 && positionCol > 0 && verti[positionRow][positionCol] == 0) return true;
	     return false;
	}

	public int getPositionRow() {
		return positionRow;
	}

	public void setPositionRow(int positionRow) {
		this.positionRow = positionRow;
	}

	public int getPositionCol() {
		return positionCol;
	}

	public void setPositionCol(int positionCol) {
		this.positionCol = positionCol;
	}

	@Override
	public void onSurfaceCreated(GL10 gl, EGLConfig config) {
		//gl.glClearColor(0.5f, 0.5f, 0.5f, 1.0f); // Set color's clear-value to
		//gl.glClearColor(1.0f, 1.0f, 1.0f, 1.0f); // Set color's clear-value to
													// black
		gl.glClearColor(0.0f, 0.0f, 0.0f, 1.0f); // Set color's clear-value to
		
		gl.glClearDepthf(1.0f); // Set depth's clear-value to farthest
		gl.glEnable(GL10.GL_DEPTH_TEST); // Enables depth-buffer for hidden
		// surface removal
		gl.glDepthFunc(GL10.GL_LEQUAL); // The type of depth testing to do
		gl.glHint(GL10.GL_PERSPECTIVE_CORRECTION_HINT, GL10.GL_NICEST); // nice
		// perspective
		// view
		gl.glShadeModel(GL10.GL_SMOOTH); // Enable smooth shading of color
		gl.glDisable(GL10.GL_DITHER); // Disable dithering for better
		// performance
		gl.glEnable(GL10.GL_LIGHTING);	
    	gl.glEnable(GL10.GL_LIGHT0);
    	//gl.glEnable(GL10.GL_LIGHT1);
    	

		// Create the GLText
		glText = new ESText(gl, context.getAssets());

		// Load the font from file (set size + padding), creates the texture
		// NOTE: after a successful call to this the font is ready for
		// rendering!
		glText.load("Roboto-Regular.ttf", 14, 2, 2); // Create Font (Height: 14
														// Pixels / X+Y Padding
														// 2 Pixels)

		// gl.glDisable(GL10.GL_DITHER); // Disable dithering for better
		// performance
		
		 // Setup Blending (NEW)
	 	gl.glColor4f(1.0f, 1.0f, 1.0f, 0.5f); // Full brightness, 50% alpha (NEW)
	 	gl.glBlendFunc(GL10.GL_SRC_ALPHA, GL10.GL_ONE); // Select blending function (NEW)

		// Setup Texture, each time the surface is created (NEW)
	 	primob3.loadBallTexture(gl, context,1);
	 	primob1.loadBallTexture(gl, context,4);
		objectball2.loadBallTexture(gl, context);
		objectballstick.loadBallTexture(gl, context,2);
		objectballbgstick.loadBallTexture(gl, context,3);
		mEarth.loadGLTexture(gl, context,2);
		//mEarth2.loadGLTexture(gl, context,6);
		mEarth3.loadGLTexture(gl, context,4);
		primobbg.loadBallTexture(gl, context, 5);
		primobflappy.loadBallTexture2(gl, context, 0);
		primobground.loadBallTexture(gl, context, 6);
		gl.glEnable(GL10.GL_TEXTURE_2D); // Enable texture (NEW)

	}

	@Override
	public void onSurfaceChanged(GL10 gl, int width, int height) {
		mywidth = width;
		myheight = height;

		gl.glViewport(0, 0, width, height);

		// Setup orthographic projection
		gl.glMatrixMode(GL10.GL_PROJECTION); // Activate Projection Matrix
		gl.glLoadIdentity(); // Load Identity Matrix
		gl.glOrthof( // Set Ortho Projection (Left,Right,Bottom,Top,Front,Back)
				0, width, 0, height, 500.0f, -500.0f);

		// Save width and height
		// this.width = width; // Save Current Width
		// this.height = height; // Save Current Height

		gl.glMatrixMode(GL10.GL_MODELVIEW); // Select model-view matrix
		gl.glLoadIdentity(); // Reset
		
		/*float[] vertices_quad = { // Vertices for the square
				-1.0f, -1.0f, 0.0f, // 0. left-bottom
				1.0f, -1.0f, 0.0f, // 1. right-bottom
				-1.0f, 1.0f, 0.0f, // 2. left-top
				1.0f, 1.0f, 0.0f // 3. right-top
		};
		
		// set background
		Bitmap bitmap = BitmapFactory.decodeStream(context.getResources()
				.openRawResource(R.drawable.splash));
		
		float tex_quad[] = {
		-100, -100, 0,
		 100, -100, 0,
		 -100,  100, 0,
		100,  100, 0
		};    
		
		gl.glEnableClientState(GL10.GL_VERTEX_ARRAY);
		gl.glVertexPointer(3, GL10.GL_FLOAT, 0, makeFloatBuffer(tex_quad));
		gl.glTexCoordPointer(3, GL10.GL_FLOAT, 0, makeFloatBuffer(vertices_quad)); // 5
		
		gl.glEnableClientState(GL10.GL_TEXTURE_COORD_ARRAY);

		//gl.glVertexPointer(2, GL_FLOAT, sizeof(GLfloat)*4, &tex_quad[0]);
		//glTexCoordPointer(2, GL_FLOAT, sizeof(GLfloat)*4, &tex_quad[2]);

		gl.glDisable(GL10.GL_DEPTH_TEST);
		gl.glDepthMask(false);
		gl.glEnable(GL10.GL_TEXTURE_2D);
		
		//gl.glGenTextures(1, textures_indek, 0);
		gl.glBindTexture(GL10.GL_TEXTURE_2D, 0);
		gl.glDrawArrays(GL10.GL_TRIANGLE_FAN, 0, 4);
		

		//glDrawArrays(GL_TRIANGLE_FAN, 0, 4);

		//glEnable(GL_DEPTH_TEST);
		gl.glEnable(GL10.GL_DEPTH_TEST);
		gl.glDepthMask(true);
		gl.glDisable( GL10.GL_TEXTURE_2D );             // Disable Texture Mapping
		*/

	}
	


	public float getxMax() {
		return xMax;
	}

	public void setxMax(int xmax) {
		xMax = xmax;
	}

	public float getxMin() {
		return xMin;
	}

	public void setxMin(int xmin) {
		xMin = xmin;
	}

	public float getyMax() {
		return yMax;
	}

	public void setyMax(int ymax) {
		yMax = ymax;
	}

	public float getyMin() {
		return yMin;
	}

	public void setyMin(int ymin) {
		yMin = ymin;
	}

	public float getspeedX() {
		return speedX;
	}

	public void setspeedX(float speedX_) {
		speedX = speedX_;
	}

	public float getspeedY() {
		return speedY;
	}

	public void setspeedY(float speedY_) {
		speedY = speedY_;
	}

	public void moveWithCollisionDetection(CopyOfESRender esRender) {
		// Get new (x,y) position
		x += speedX;
		y += speedY;
		// Detect collision and react
		if (x + radius > esRender.getxMax()) {
			speedX = -speedX;
			x = esRender.getxMax() - radius;
			this.jumlah_pantulan+=1;
		} else if (x - radius < esRender.getxMin()) {
			speedX = -speedX;
			x = esRender.getxMin() + radius;
			this.jumlah_pantulan+=1;
		}
		if (y + radius > esRender.getyMax()) {
			speedY = -speedY;
			y = esRender.getyMax() - radius;
			this.jumlah_pantulan+=1;
		} else if (y - radius < esRender.getyMin()) {
			speedY = -speedY;
			y = esRender.getyMin() + radius;
			this.jumlah_pantulan+=1;
		}
		
		// pengkondisian penghitungan pantulan mulai dari nol
		if(Run_Mode==0){
			this.jumlah_pantulan-=4;
			this.Run_Mode=1;
		}
	}

	public void set(int x, int y, int width, int height) {
		xMin = x;
		// xMax = x + width - 1;
		xMax = x + width;
		yMin = y;
		// yMax = y + height - 1;
		yMax = y + height;
	}
}