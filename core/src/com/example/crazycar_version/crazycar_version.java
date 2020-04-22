package com.example.crazycar_version;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;

import java.util.Random;

public class crazycar_version extends ApplicationAdapter implements InputProcessor {

	SpriteBatch spriteBatch;

	boolean touchActive = false;
    OrthographicCamera orthographicCamera;

    int gamestate = 0;

    //skore
	int score = 1;
	BitmapFont scoreBitmap;

	//game over
	BitmapFont gameover;

	//gas amount
	int gasAmaount = 20;
	BitmapFont gasAmaountBitmap;
	int useGas =0;

   //background operations
	Texture background;
	int backgroundX,backgroundY,backgroundWidth,backgroundHeight;

	//stones operations
	Texture stones;
	int stonesDownX, stonesDownY, stonesDownWidht, stonesDownHeight;
	int stonesUpX, stonesUpY, stonesUpWidht, stonesUpHeight;
	//car operations
	Texture car;
	int carX,carY,carWidht,carHeight;
    Rectangle rectangleCar;
    Circle crcCar;
    ShapeRenderer shapeRenderer;

    //Direction
	Texture downArrow;
	Rectangle rctDownArrow;
	int downArrowX;
	int downArrowY;
	int downArrowWidth;
	int downArrowHeight;

	Texture upArrow;
	Rectangle rctUpArrow;
	int upArrowX;
	int upArrowY;
	int upArrowWidth;
	int upArrowHeight;

	Texture speedArrow;
	Rectangle rctspeedArrow;
	int speedArrowX;
	int speedArrowY;
	int speedArrowWidht;
	int speedArrowHeight;

	//Tree
	Texture tree;
	Rectangle rctTree;
	int treeX,treeYUp,treeYDown,treeWidht,treeHeight;
	float speedTree =2;
	int numberoftree = 2;
	float [] treeSetX = new float[numberoftree];
	int distancetree = 0;

	//enemyCar
	Texture enemyCar;
    Texture enemyCar2;
	Rectangle rctenemyCar;
    Rectangle rctenemyCar2;
	int enemyCarX,enemyCarY,enemyCarWidth,enemyCarHeight;
	float speedEnemyCar=3;
	int numberofcar=2;
	float [] enemyCarSetX = new float[numberofcar];
    float [] enemyCarSetX2 = new float[numberofcar];
	float [] enemyCarOffSetX = new float[numberofcar];
    float [] enemyCarOffSetX2 = new float[numberofcar];
	int dinstanceEnemycar = 0;
	Random random;
	Circle[] crcEnemeyCar1;
	Circle[] crcEnemeyCar2;

	// gascar
	Texture gasCarBarrel;
	Rectangle rctgasCar;
	int gasCarX,gasCarY,gasCarWidht,gasCarHeight;
	Circle crcGasCar;

	
	@Override
	public void create () {
		Gdx.input.setInputProcessor(this);
        spriteBatch = new SpriteBatch();
        orthographicCamera = new OrthographicCamera();
        orthographicCamera.setToOrtho(false,Gdx.graphics.getWidth(),Gdx.graphics.getHeight());
        //create for background
		background = new Texture("road.png");
		backgroundX=0;
		backgroundY = (Gdx.graphics.getHeight()/5);
		backgroundWidth = Gdx.graphics.getWidth();
		backgroundHeight = backgroundY*3;
		//create for stones
		stones = new Texture("stones.png");
		stonesDownX = 0;
		stonesDownY = 0;
		stonesDownWidht =backgroundWidth;
		stonesDownHeight =backgroundY;
		stonesUpX=0;
		stonesUpY=backgroundY*4;
		stonesUpWidht=backgroundWidth;
		stonesUpHeight=backgroundY;
		//create for car
		car = new Texture("car.png");
		carX = Gdx.graphics.getWidth()/15;
		carY = Gdx.graphics.getHeight()/2;
		carWidht=Gdx.graphics.getWidth()/12;
		carHeight=Gdx.graphics.getHeight()/12;
		rectangleCar = new Rectangle(carX,carY,carWidht,carHeight);
		crcCar = new Circle();

        //for score
		scoreBitmap = new BitmapFont();
        scoreBitmap.setColor(Color.BLACK);
        scoreBitmap.getData().setScale(4);
        //for gasAmount bitmap
		gasAmaountBitmap = new BitmapFont();
		gasAmaountBitmap.setColor(Color.BLACK);
		gasAmaountBitmap.getData().setScale(4);
        //for gameover bitmap
		gameover = new BitmapFont();
		gameover.setColor(Color.BLACK);
		gameover.getData().setScale(10);
		//direction
		//---------
		   //down
		   downArrow = new Texture("down.png");
		   downArrowX = Gdx.graphics.getWidth()/15;
		   downArrowY = (Gdx.graphics.getHeight()/15);;
		   downArrowWidth = Gdx.graphics.getWidth()/10;
		   downArrowHeight = Gdx.graphics.getHeight()/10;
		   rctDownArrow = new Rectangle(downArrowX,downArrowY,downArrowWidth,downArrowHeight);
		   //up
		   upArrow = new Texture("up.png");
		   upArrowX = Gdx.graphics.getWidth()/5;
		   upArrowY = (Gdx.graphics.getHeight()/15);
		   upArrowWidth = Gdx.graphics.getWidth()/10;
		   upArrowHeight = Gdx.graphics.getHeight()/10;
		   rctUpArrow =new Rectangle(upArrowX,upArrowY,upArrowWidth,upArrowHeight);
		   //Speed
		   speedArrow = new Texture("right.png");
		   speedArrowX = (Gdx.graphics.getWidth()/4)*3;
		   speedArrowY =(Gdx.graphics.getHeight()/10)-10;
		   speedArrowWidht = Gdx.graphics.getWidth()/15;
		   speedArrowHeight = Gdx.graphics.getHeight()/10;
		   rctspeedArrow = new Rectangle(speedArrowX,speedArrowY,speedArrowWidht,speedArrowHeight);

        //trees
		tree = new Texture("tree.png");
		treeX = Gdx.graphics.getWidth();
		treeYDown= stonesDownHeight/5;
		treeWidht = Gdx.graphics.getWidth()/15;
		treeHeight = stonesDownHeight/2;
		treeYUp=stonesUpY;
		rctTree = new Rectangle(treeX,treeYDown,treeWidht,treeHeight);
		distancetree = Gdx.graphics.getWidth()/2;
		for(int i = 0;i < numberoftree;i++){
			treeSetX[i]=rctTree.x-(treeWidht/2)*i*distancetree;
		}
		//enemycar
		enemyCar = new Texture("redcar.png");
		enemyCar2 = new Texture("redcar.png");
		enemyCarX = Gdx.graphics.getWidth();
		enemyCarY = Gdx.graphics.getHeight()/2;
		enemyCarWidth = Gdx.graphics.getWidth()/10;
		enemyCarHeight = Gdx.graphics.getHeight()/10;
		rctenemyCar = new Rectangle(enemyCarX,enemyCarY,enemyCarWidth,enemyCarHeight);
        rctenemyCar2 = new Rectangle(enemyCarX,enemyCarY,enemyCarWidth,enemyCarHeight);
		dinstanceEnemycar = Gdx.graphics.getWidth()/2;
		random = new Random();

		crcEnemeyCar1 = new Circle[numberofcar];
		crcEnemeyCar2 = new Circle[numberofcar];

        for(int a=0;a<numberofcar;a++){
			enemyCarOffSetX[a]=(random.nextFloat())*(backgroundHeight);
			enemyCarOffSetX2[a]=(random.nextFloat())*(backgroundHeight);
			enemyCarSetX[a]=rctenemyCar.x+(enemyCarWidth/8);
			enemyCarSetX2[a]=rctenemyCar.x+(enemyCarWidth/8);

			crcEnemeyCar1[a] = new Circle();
			crcEnemeyCar2[a] = new Circle();

		}

         //for gascar
	    gasCarBarrel = new Texture("barel.png");
        gasCarX = Gdx.graphics.getWidth();
        gasCarY =  Gdx.graphics.getHeight()/2;
        gasCarWidht =Gdx.graphics.getWidth()/10;
		gasCarHeight =Gdx.graphics.getHeight()/9;
		rctgasCar = new Rectangle(gasCarX,gasCarY,gasCarWidht,gasCarHeight);
        crcGasCar = new Circle();

        shapeRenderer = new ShapeRenderer();
	}

	@Override
	public void render () {

		spriteBatch.begin();
		//for drawing characters

		spriteBatch.draw(background,backgroundX,backgroundY,backgroundWidth,backgroundHeight);
		spriteBatch.draw(stones,stonesDownX,stonesDownY,stonesDownWidht,stonesDownHeight);
		spriteBatch.draw(stones,stonesUpX,stonesUpY,stonesUpWidht,stonesUpHeight);
		spriteBatch.draw(car,rectangleCar.x,rectangleCar.y,rectangleCar.width,rectangleCar.height);
		spriteBatch.draw(downArrow,rctDownArrow.x,rctDownArrow.y,rctDownArrow.width,rctDownArrow.height);
		spriteBatch.draw(upArrow,rctUpArrow.x,rctUpArrow.y,rctUpArrow.width,rctUpArrow.height);
		spriteBatch.draw(speedArrow,rctspeedArrow.x,rctspeedArrow.y,rctspeedArrow.width,rctspeedArrow.height);




		//for move tree
        if(gamestate == 1) {


			score=score+1;

       //for gasamaount operatin
				   useGas++;
					if(useGas > 150){
						int AgasAmaount = useGas/150;
						gasAmaount=gasAmaount-AgasAmaount;
						useGas=0;

					}
					// gas varillerin çıkma aralıkları
			if((gasAmaount%5)==0 && gasAmaount<20) {
				spriteBatch.draw(gasCarBarrel, rctgasCar.x, rctgasCar.y, rctgasCar.width, rctgasCar.height);
				rctgasCar.x-=12;
			}
			else {
				rctgasCar.x=Gdx.graphics.getWidth();
			}

					if(gasAmaount == 0){

						gamestate = 2;
					}
			gasAmaountBitmap.draw(spriteBatch,String.valueOf("GAS - "+gasAmaount + "%"),Gdx.graphics.getWidth()/2,(Gdx.graphics.getHeight()*7)/8);  //gas operation





        	for(int i=0 ;i<numberoftree;i++){
				if (treeSetX[i]<-rctTree.y)
				{
					treeSetX[i]=treeSetX[i]+numberoftree*distancetree;

				}else{
					treeSetX[i]=treeSetX[i]-speedTree;
				}
			spriteBatch.draw(tree, treeSetX[i], treeYUp, rctTree.width, rctTree.height);
            spriteBatch.draw(tree, treeSetX[i], rctTree.y, rctTree.width, rctTree.height);
        	}


        	for(int a = 0; a<numberofcar;a++){
				if (enemyCarSetX[a]<-rctenemyCar.y)
				{
					enemyCarSetX[a]=enemyCarSetX[a]+numberofcar*dinstanceEnemycar;

					enemyCarOffSetX[a]=(random.nextFloat()-0.5f)*(backgroundHeight);
                    enemyCarOffSetX2[a]=(random.nextFloat()-0.5f)*(backgroundHeight);

				}else{
					enemyCarSetX[a]=enemyCarSetX[a]-speedEnemyCar;
                    enemyCarSetX2[a]=enemyCarSetX2[a]-speedEnemyCar;
				}
                if((Gdx.graphics.getHeight()/2+enemyCarOffSetX[a])<stonesUpY-rctenemyCar.height ){
				spriteBatch.draw(enemyCar,enemyCarSetX[a]+(Gdx.graphics.getWidth()/4),Gdx.graphics.getHeight()/2+enemyCarOffSetX[a],rctenemyCar.width,rctenemyCar.height);
					crcEnemeyCar1[a] = new Circle(enemyCarSetX[a]+(Gdx.graphics.getWidth()/3)-Gdx.graphics.getWidth()/25,Gdx.graphics.getHeight()/2+enemyCarOffSetX[a]+Gdx.graphics.getWidth()/35,Gdx.graphics.getWidth()/40);
                  float carBtwn = (Gdx.graphics.getHeight()/2+enemyCarOffSetX[a])+rctenemyCar.width;

                  if((Gdx.graphics.getHeight()/2+enemyCarOffSetX2[a])<stonesUpY-rctenemyCar.height ) {

                  	spriteBatch.draw(enemyCar2, enemyCarSetX[a] + (20) + (Gdx.graphics.getWidth() / 6), Gdx.graphics.getHeight() / 2 + enemyCarOffSetX2[a], rctenemyCar.width, rctenemyCar.height);
					  crcEnemeyCar2[a] = new Circle(enemyCarSetX[a]  + (Gdx.graphics.getWidth() / 6)+Gdx.graphics.getWidth()/15,Gdx.graphics.getHeight() / 2 + enemyCarOffSetX2[a]+Gdx.graphics.getWidth()/35,Gdx.graphics.getWidth()/40);

				  }
                  }


        	}





        }
        else if(gamestate == 0){
			if(Gdx.input.justTouched()){
				gamestate = 1;
			}
        }
        else if(gamestate == 2){
			gameover.draw(spriteBatch,"GAME OVER",(Gdx.graphics.getWidth()/4),Gdx.graphics.getHeight()/2);
			if(Gdx.input.justTouched()){

                for(int a=0;a<numberofcar;a++){
					enemyCarOffSetX[a]=(random.nextFloat())*(backgroundHeight);
					enemyCarOffSetX2[a]=(random.nextFloat())*(backgroundHeight);
					enemyCarSetX[a]=rctenemyCar.x+(enemyCarWidth/8)*a*dinstanceEnemycar;
					enemyCarSetX2[a]=rctenemyCar.x+(enemyCarWidth/8)*a*dinstanceEnemycar;

					crcEnemeyCar1[a] = new Circle();
					crcEnemeyCar2[a] = new Circle();

                }
				gamestate = 1;
				gasAmaount=20;
				score=0;

			}
		}
		scoreBitmap.draw(spriteBatch,String.valueOf(score),carX,(Gdx.graphics.getHeight()*7)/8); //score operation
		spriteBatch.end();

		//for circle operation
		crcCar.set(rectangleCar.x+Gdx.graphics.getWidth()/25,rectangleCar.y+Gdx.graphics.getWidth()/40,Gdx.graphics.getWidth()/40);
		crcGasCar.set(rctgasCar.x+Gdx.graphics.getWidth()/20,rctgasCar.y+Gdx.graphics.getWidth()/35,Gdx.graphics.getWidth()/30);

		shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
		shapeRenderer.setColor(Color.BLACK);
		//shapeRenderer.circle(crcCar.x,crcCar.y,crcCar.radius);

        for(int i = 0;i<numberofcar;i++){
        	//shapeRenderer.circle(crcEnemeyCar1[i].x,crcEnemeyCar1[i].y,crcEnemeyCar1[i].radius);
			//shapeRenderer.circle(crcEnemeyCar2[i].x,crcEnemeyCar2[i].y,crcEnemeyCar2[i].radius);
			if (Intersector.overlaps(crcCar,crcEnemeyCar1[i]) || Intersector.overlaps(crcCar,crcEnemeyCar2[i])){
				gamestate=2;
			}
		}
		//shapeRenderer.circle(crcGasCar.x,crcGasCar.y,crcGasCar.radius);
		shapeRenderer.end();
		if (Intersector.overlaps(crcCar,crcGasCar)){
			gasAmaount+=2;
		}

//for direction arrow
		if(touchActive){ //for touch active operation
			Vector3 touchPos = new Vector3();
			touchPos.set(Gdx.input.getX(), Gdx.input.getY(), 0);
			orthographicCamera.unproject(touchPos);

			if(rctDownArrow.contains(touchPos.x,touchPos.y) && rectangleCar.y>stonesDownHeight){ //down arrow
				rectangleCar.y-=400f*Gdx.graphics.getDeltaTime();
			}
			else if(rctUpArrow.contains(touchPos.x,touchPos.y) && rectangleCar.y <stonesUpY-rectangleCar.height){  //up arrow
				rectangleCar.y+=400f*Gdx.graphics.getDeltaTime();
			}
			if (rctspeedArrow.contains(touchPos.x,touchPos.y)){ //speed arrow
				speedTree=8;
				speedEnemyCar=10;
				score=score+2;
			}else {
				speedTree = 4;
				speedEnemyCar=8;
			}
		}

	}
	
	@Override
	public void dispose () {

	}

	@Override
	public boolean keyDown(int keycode) {
		return false;
	}

	@Override
	public boolean keyUp(int keycode) {
		return false;
	}

	@Override
	public boolean keyTyped(char character) {
		return false;
	}

	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		touchActive = true;
		return false;
	}

	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		touchActive = false;
		return false;
	}

	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer) {
		return false;
	}

	@Override
	public boolean mouseMoved(int screenX, int screenY) {
		return false;
	}

	@Override
	public boolean scrolled(int amount) {
		return false;
	}
}
