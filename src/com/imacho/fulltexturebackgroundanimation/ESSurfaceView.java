package com.imacho.fulltexturebackgroundanimation;

import android.content.Context;
//import android.graphics.PixelFormat;
import android.graphics.Point;
import android.opengl.GLSurfaceView;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MotionEvent;

/**
 * A view container where OpenGL ES graphics can be drawn on screen. This view
 * can also be used to capture touch events, such as a user interacting with
 * drawn objects.
 */
public class ESSurfaceView extends GLSurfaceView {

	private final ESRender esRender;
	private float previousX;
	private float previousY;

	/*
	 * public float initx_stick = 425; public float inity_stick = 267; public
	 * Point _touchingPoint = new Point(425,267); public Point _pointerPosition
	 * = new Point(220,150);
	 */

	public float initx_stick = 1013;
	public float inity_stick = 500;
	public Point _touchingPoint = new Point(1013, 500);
	public Point _pointerPosition = new Point(220, 150);
	private Boolean _dragging = false;
	private int flag_first = 0;

	private float mypointer_awal_x = 0;
	private float mypointer_awal_y = 0;

	public ESSurfaceView(Context context) {
		super(context);

		// Set the Renderer for drawing on the GLSurfaceView
		esRender = new ESRender(context);
		setRenderer(esRender);

		// To enable keypad
		this.setFocusable(true);
		this.requestFocus();

		// To enable touch mode
		this.setFocusableInTouchMode(true);

		// Render the view only when there is a change in the drawing data
		// merender hanya ketika ada perubahan/ event
		// setRenderMode(GLSurfaceView.RENDERMODE_WHEN_DIRTY);
		
		//set background
		//glSurfaceView = ...

		/*glSurfaceView.setEGLConfigChooser(8, 8, 8, 8, 16, 0);
		glSurfaceView.getHolder().setFormat(PixelFormat.TRANSLUCENT);
		glSurfaceView.setBackgroundResource(R.drawable.my_background);
		glSurfaceView.setZOrderOnTop(true);

		glSurfaceView.setRenderer(...);
		glSurfaceView.setRenderMode(...);*/
		
		//this.setEGLConfigChooser(8, 8, 8, 8, 16, 0);
		//this.getHolder().setFormat(PixelFormat.TRANSLUCENT);
		//this.setBackgroundResource(R.drawable.splash);
		//this.setZOrderOnTop(true);

		//glSurfaceView.setRenderer(...);
		//glSurfaceView.setRenderMode(...);
		
	}

	@Override
	public boolean onTouchEvent(MotionEvent v) {
		// MotionEvent reports input details from the touch screen
		// and other input controls. In this case, we are only
		// interested in events where the touch position changed.

		/*
		 * initx_stick = esRender.getWidthObject()-esRender.getRadius();
		 * inity_stick = 0.0f; _touchingPoint.x = (int) initx_stick;
		 * _touchingPoint.y= (int) inity_stick;
		 * 
		 * _pointerPosition.x = (int) initx_stick; _pointerPosition.y= (int)
		 * inity_stick;
		 */

		//update(v);
		kontrol(v);

		float currentX = v.getX();
		float currentY = v.getY();
		float deltaX, deltaY;

		// float scalingFactor = 0.50f / ((esRender.xMax > esRender.yMax) ?
		// esRender.yMax
		// : esRender.xMax);

		switch (v.getAction()) {
		case MotionEvent.ACTION_DOWN:
			//Log.v("Test Action Down", "action down working");

			// esRender.setX_anim_stick(-esRender.getRadius());
			// esRender.setY_anim_stick(0.0f);

			// membuat animasi pergerakan tombol joystick

			// bound to a box disekitar joystick
			/*
			 * if( currentX >= _touchingPoint.x-esRender.getRadius() && currentX
			 * <= _touchingPoint.x+esRender.getRadius()){
			 * 
			 * if( currentY >= _touchingPoint.y-esRender.getRadius() && currentY
			 * <= _touchingPoint.y+esRender.getRadius()){
			 * 
			 * // lakukan pergerakan pada stick
			 * esRender.setX_anim_stick(esRender.getRadius());
			 * esRender.setY_anim_stick(esRender.getRadius());
			 * 
			 * }
			 * 
			 * }
			 */
			// break;
			// requestRender();
			// case MotionEvent.ACTION_POINTER_DOWN:
			// Log.v("Test Action ACTION_POINTER_DOWN", "action working");
			// esRender.setX_anim_stick(-esRender.getRadius());
			// esRender.setY_anim_stick(-esRender.getRadius());

			// membuat animasi pergerakan tombol joystick

			// bound to a box disekitar joystick
			/*
			 * if( currentX >= _touchingPoint.x-esRender.getRadius() && currentX
			 * <= _touchingPoint.x+esRender.getRadius()){
			 * 
			 * if( currentY >= _touchingPoint.y-esRender.getRadius() && currentY
			 * <= _touchingPoint.y+esRender.getRadius()){
			 * 
			 * // lakukan pergerakan pada stick
			 * esRender.setX_anim_stick(esRender.getRadius());
			 * esRender.setY_anim_stick(esRender.getRadius());
			 * 
			 * }
			 * 
			 * }
			 */
			// requestRender();
		case MotionEvent.ACTION_UP:
			//Log.v("Test Action ACTION_UP", "action working");
			// esRender.setX_anim_stick(0.0f);
			// esRender.setY_anim_stick(-esRender.getRadius());
			// requestRender();
			// case MotionEvent.ACTION_MOVE:
			// Log.v("Test Action ACTION_POINTER_DOWN", "action working");
			// requestRender();
			// case MotionEvent.ACTION_UP:
			// Log.v("Test Action Up", "action up working");
			// requestRender();
		case MotionEvent.ACTION_MOVE:
			
			// Modify rotational angles according to movement
			/*deltaX = currentX - previousX;
			deltaY = currentY - previousY;

			// esRender.setspeedX(esRender.getspeedX()+ (deltaX/getWidth()));
			// esRender.setspeedY(esRender.getspeedY()+ (deltaY/getHeight()));

			esRender.setspeedX(esRender.getspeedX() + deltaX / 100);
			esRender.setspeedY(esRender.getspeedY() + deltaY / 100);
			*/
			//requestRender();
		}

		// Save current x, y
		previousX = currentX;
		previousY = currentY;
		return true; // Event handled
		// break;
	}

	public void kontrol(MotionEvent event){
		Point _touchingPoint = new Point(1013, 500);
		
		if (event.getAction() == MotionEvent.ACTION_DOWN) {
			_dragging = true;
		} else if (event.getAction() == MotionEvent.ACTION_UP) {
			_dragging = false;
		}
		
		if(_dragging){
			_touchingPoint.x = (int) event.getX();
			_touchingPoint.y = esRender.myheight - (int) event.getY();
			esRender.x_touch = _touchingPoint.x;
			esRender.y_touch = _touchingPoint.y;
			
			// TODO : calc this thing!!!!
			
		} 
	}
	
	private MotionEvent lastEvent;

	public void update(MotionEvent event) {

		// initx_stick = 1066.0f-1.5f*esRender.getRadius();
		// inity_stick =0.5f*esRender.getRadius();

		if (event == null && lastEvent == null) {
			return;
		} else if (event == null && lastEvent != null) {
			event = lastEvent;
		} else {
			lastEvent = event;
		}
		// drag drop
		if (event.getAction() == MotionEvent.ACTION_DOWN) {
			_dragging = true;
			flag_first = 0;
		} else if (event.getAction() == MotionEvent.ACTION_UP) {
			_dragging = false;
		}

		// penanda untuk menyimpan koordinat yang pertama kali diklik

		if (_dragging) {

			// get the pos
			_touchingPoint.x = (int) event.getX();
			_touchingPoint.y = (int) event.getY();

			esRender.setX_touch(_touchingPoint.x);
			esRender.setY_touch(esRender.getMyheight() - _touchingPoint.y);

			esRender.setJarak_center((float) ((Math.pow(_touchingPoint.x
					- (esRender.getMywidth() - 2 * esRender.getRadius()), 2)) + (Math
					.pow(esRender.getMyheight() - _touchingPoint.y - 2
							* esRender.getRadius(), 2))));
			
			
			if(esRender.getJarak_center()<=Math.pow(25,2)){
				//esRender.setX_graf(esRender.getX_graf()-10);
				esRender.setY_graf(esRender.getY_graf()-10);
			}else{
				esRender.setY_graf(esRender.getY_graf()+10);
			}
			

			// membuat radius touch screen
			if (_touchingPoint.x >= (esRender.getMywidth() - 200)
					&& _touchingPoint.y >= (esRender.getMyheight() - 200)
					&& (((Math.pow(
							_touchingPoint.x
									- (esRender.getMywidth() - 2 * esRender
											.getRadius()), 2)) + (Math.pow(
							esRender.getMyheight() - _touchingPoint.y - 2
									* esRender.getRadius(), 2))) > Math.pow(
							0.5 * esRender.getRadius(), 2))) {

				Music.playOnce(esRender.context, R.raw.fb);

				// membuat penanda untuk mendapatkan point awal touch
				if (flag_first == 0) {
					esRender.setX_pointer_awal(_touchingPoint.x);
					mypointer_awal_x = esRender.getX_pointer_awal();
					esRender.setY_pointer_awal(_touchingPoint.y);
					mypointer_awal_y = esRender.getY_pointer_awal();

					flag_first = 1;
				}

				// kodisi jika titik awal sama dengan titik pointer
				if ((mypointer_awal_x == _touchingPoint.x)
						&& (mypointer_awal_y == _touchingPoint.y)) {
					mypointer_awal_x = esRender.getMywidth() - 100;
					mypointer_awal_y = esRender.getMyheight() - 100;
				}

				esRender.setX_pointer(_touchingPoint.x);
				esRender.setY_pointer(_touchingPoint.y);

				esRender.setX_lebar_layar(esRender.getMywidth());
				esRender.setY_tinggi_layar(esRender.getMyheight());

				// get the angle
				double myangle = -Math
						.atan2(_touchingPoint.y - mypointer_awal_y,
								_touchingPoint.x - mypointer_awal_x)
						/ (Math.PI / 180);

				esRender.setSudut_pointer((float) myangle);

				esRender.setX_anim_stick((float) (26.0f * Math.cos(myangle
						* (Math.PI / 180))));
				esRender.setY_anim_stick((float) (26.0f * Math.sin(myangle
						* (Math.PI / 180))));

				// set arah joystick
				if ((float) myangle >= 45 && (float) myangle <= 135) {
					esRender.setArah_joystick("Atas");

					if (esRender.getNavigate() == 0 && esRender.goForward())
						esRender.setPositionRow(esRender.getPositionRow() - 1);
					else if (esRender.getNavigate() == 1
							&& esRender.goForward())
						esRender.setPositionCol(esRender.getPositionCol() + 1);
					else if (esRender.getNavigate() == 2
							&& esRender.goForward())
						esRender.setPositionRow(esRender.getPositionRow() + 1);
					else if (esRender.getNavigate() == 3
							&& esRender.goForward())
						esRender.setPositionCol(esRender.getPositionCol() - 1);
					Log.v("Test Action KEYCODE_T", "action working");

					esRender.setX_player((esRender.getPositionCol() * esRender
							.getWidthObject())
							+ (esRender.getWidthObject() / 2));
					esRender.setY_player(((esRender.getNumberRow()
							- esRender.getPositionRow() - 1) * esRender
								.getWidthObject())
							+ (esRender.getWidthObject() / 2));

				} else if (((float) myangle >= 0 && (float) myangle < 45)
						|| ((float) myangle >= -44 && (float) myangle <= 0)) {
					esRender.setArah_joystick("Kanan");

					esRender.setNavigate(esRender.getNavigate() + 1);
				} else {
					esRender.setArah_joystick("Kiri");
					esRender.setNavigate(esRender.getNavigate() - 1);
				}

				// reset Navigate
				if (esRender.getNavigate() < 0) {
					esRender.setNavigate(3);
				}
				esRender.setNavigate(esRender.getNavigate() % 4);

			}

			/*
			 * //membuat penanda if(flag_first==0){
			 * esRender.setX_pointer_awal(_touchingPoint.x);
			 * esRender.setY_pointer_awal(_touchingPoint.y); flag_first=1; }
			 * 
			 * esRender.setX_pointer(_touchingPoint.x);
			 * esRender.setY_pointer(_touchingPoint.y);
			 * 
			 * esRender.setX_lebar_layar(esRender.getMywidth());
			 * esRender.setY_tinggi_layar(esRender.getMyheight());
			 * 
			 * esRender.setX_anim_stick(_touchingPoint.x);
			 * esRender.setY_anim_stick(_touchingPoint.y);
			 */

			// bound to a box
			if (_touchingPoint.x < 400) {
				_touchingPoint.x = 400;
			}
			if (_touchingPoint.x > 450) {
				_touchingPoint.x = 450;
			}
			if (_touchingPoint.y < 240) {
				_touchingPoint.y = 240;
			}
			if (_touchingPoint.y > 290) {
				_touchingPoint.y = 290;
			}

			// get the angle
			double angle = Math.atan2(_touchingPoint.y - inity_stick,
					_touchingPoint.x - initx_stick) / (Math.PI / 180);

			// Move the beetle in proportion to how far
			// the joystick is dragged from its center
			_pointerPosition.y += Math.sin(angle * (Math.PI / 180))
					* (_touchingPoint.x / 70);
			_pointerPosition.x += Math.cos(angle * (Math.PI / 180))
					* (_touchingPoint.x / 70);

			// esRender.setX_anim_stick(_touchingPoint.x);
			// esRender.setY_anim_stick(_touchingPoint.y);

			// make the pointer go thru
			if (_pointerPosition.x > 480) {
				_pointerPosition.x = 0;
			}

			if (_pointerPosition.x < 0) {
				_pointerPosition.x = 480;
			}

			if (_pointerPosition.y > 320) {
				_pointerPosition.y = 0;
			}
			if (_pointerPosition.y < 0) {
				_pointerPosition.y = 320;
			}

		} else if (!_dragging) {

			flag_first = 0;

			// Snap back to center when the joystick is released
			_touchingPoint.x = (int) initx_stick;
			_touchingPoint.y = (int) inity_stick;

			esRender.setX_anim_stick(0.0f);
			esRender.setY_anim_stick(0.0f);

			// shaft.alpha = 0;
		}
	}

	// Key-up event handler
	@Override
	public boolean onKeyUp(int keyCode, KeyEvent event) {
		switch (keyCode) {
		case KeyEvent.KEYCODE_A: // mengurangi kecepatan object
			if ((esRender.getspeedX() - 0.05f > 0)) {
				esRender.setspeedX(esRender.getspeedX() - 0.05f);
			}
			if ((esRender.getspeedX() - 0.05f < 0)) {
				esRender.setspeedX(0.0f);
			}
			if ((esRender.getspeedY() - 0.05f >= 0)) {
				esRender.setspeedY(esRender.getspeedY() - 0.05f);
			}
			if ((esRender.getspeedY() - 0.05f < 0)) {
				esRender.setspeedY(0.0f);
			}
			Log.v("Test Action KEYCODE_A", "action working");
			break;
		case KeyEvent.KEYCODE_T: // up
			if (esRender.getNavigate() == 0 && esRender.goForward())
				esRender.setPositionRow(esRender.getPositionRow() - 1);
			else if (esRender.getNavigate() == 1 && esRender.goForward())
				esRender.setPositionCol(esRender.getPositionCol() + 1);
			else if (esRender.getNavigate() == 2 && esRender.goForward())
				esRender.setPositionRow(esRender.getPositionRow() + 1);
			else if (esRender.getNavigate() == 3 && esRender.goForward())
				esRender.setPositionCol(esRender.getPositionCol() - 1);
			Log.v("Test Action KEYCODE_T", "action working");

			esRender.setX_player((esRender.getPositionCol() * esRender
					.getWidthObject()) + (esRender.getWidthObject() / 2));
			esRender.setY_player(((esRender.getNumberRow()
					- esRender.getPositionRow() - 1) * esRender
						.getWidthObject()) + (esRender.getWidthObject() / 2));
			break;
		case KeyEvent.KEYCODE_G:
			Log.v("Test Action KEYCODE_G", "action working");
			break;
		case KeyEvent.KEYCODE_H: // right
			esRender.setNavigate(esRender.getNavigate() + 1);
			Log.v("Test Action KEYCODE_H", "action working");
			break;
		case KeyEvent.KEYCODE_F: // left
			esRender.setNavigate(esRender.getNavigate() - 1);
			Log.v("Test Action KEYCODE_F", "action working");
			break;
		}

		if (esRender.getNavigate() < 0)
			esRender.setNavigate(3);
		esRender.setNavigate(esRender.getNavigate() % 4);

		return true; // Event handled
	}
}
