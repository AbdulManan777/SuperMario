package com.supermario.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.Timer;
import com.sun.jndi.toolkit.url.Uri;


import java.util.ArrayList;
import java.util.Random;

public class SuperMario extends ApplicationAdapter {
	SpriteBatch batch;
	Texture background;
	Texture[] mario;
	int marioPos;
	int pausemario=0;
	float gravity=0.2f;
	float velocity=0;
	float gravity2=0.2f;
	float velocity2=0;
	int marioYheight=0;
	int pauseMonster=0;

	Random rand;
	Rectangle marioRectangle;
	ArrayList<Integer> coinXpos=new ArrayList<Integer>();
	ArrayList<Integer> coinYpos=new ArrayList<Integer>();

	Texture coin;
	int coinCount=0;

	Texture life;
	int heart_count=0;
	ArrayList<Rectangle>heartRectangle=new ArrayList<Rectangle>();
	ArrayList<Integer> heartXpos=new ArrayList<Integer>();
	ArrayList<Integer> heartYpos=new ArrayList<Integer>();


	Random rand2;
	ArrayList<Integer> bombXpos=new ArrayList<Integer>();
	ArrayList<Integer> bombYpos=new ArrayList<Integer>();

	ArrayList<Rectangle>coinRectangle=new ArrayList<Rectangle>();

	ArrayList<Rectangle>bombRectangle=new ArrayList<Rectangle>();
	Texture bomb;
	int bombCount=0;

	Texture duck;
	int duckCount=0;

	Texture monster;
	int monsterCount=0;
	ArrayList<Integer> monsterXpos=new ArrayList<Integer>();
	ArrayList<Integer> monsterYpos=new ArrayList<Integer>();
	ArrayList<Rectangle>monsterRectangle=new ArrayList<Rectangle>();

	Texture bullet;
	int bullet_count=0;
	ArrayList<Rectangle>bulletRectangle=new ArrayList<Rectangle>();
	ArrayList<Integer> bulletXpos=new ArrayList<Integer>();
	ArrayList<Integer> bulletYpos=new ArrayList<Integer>();

	ArrayList<Rectangle>duckRectangle=new ArrayList<Rectangle>();
	ArrayList<Integer> duckXpos=new ArrayList<Integer>();
	ArrayList<Integer> duckYpos=new ArrayList<Integer>();
	Music jump;
	Music attack;
	Music coin_sound;
	Music level_up;
	Music won;
	Music monster_level;
	Music gameover;


	Texture dizzyMario;
	Texture kingdom;

	int score=0;
	BitmapFont bt;
	BitmapFont level_name;
	BitmapFont heart;






	int monster_Yheight=0;
	int gameState=0;

	int level=1;

	int lives=3;

	boolean flag=false;

	int exitcount=0;
	@Override
	public void create () {
		batch = new SpriteBatch();
		background=new Texture("bg.png");
		mario=new Texture[4];
		mario[0]=new Texture("frame-1.png");
		mario[1]=new Texture("frame-2.png");
		mario[2]=new Texture("frame-3.png");
		mario[3]=new Texture("frame-4.png");
		coin=new Texture("coin.png");
		bomb=new Texture("tidda.png");
		dizzyMario=new Texture("dizzy-1.png");
		monster=new Texture("real_monster.png");
		bullet=new Texture("bullet.png");
		duck=new Texture("duck.png");
		kingdom=new Texture("pngegg.png");
		life=new Texture("heart.png");
		jump=Gdx.audio.newMusic(Gdx.files.internal("jump_soun.mp3"));
		attack=Gdx.audio.newMusic(Gdx.files.internal("attack.wav"));
		coin_sound=Gdx.audio.newMusic(Gdx.files.internal("coin_collect.wav"));
		level_up=Gdx.audio.newMusic(Gdx.files.internal("level_up.mp3"));
		won=Gdx.audio.newMusic(Gdx.files.internal("win.mp3"));
		monster_level=Gdx.audio.newMusic(Gdx.files.internal("level3.mp3"));
		gameover=Gdx.audio.newMusic(Gdx.files.internal("gameover.mp3"));

		marioYheight=Gdx.graphics.getHeight()/2;
		monster_Yheight=Gdx.graphics.getHeight()/2;
		rand=new Random();
		rand2=new Random();
		bt=new BitmapFont();
		bt.setColor(Color.WHITE);
		bt.getData().setScale(10);


		level_name=new BitmapFont();
		level_name.setColor(Color.RED);
		level_name.getData().setScale(10);

		heart=new BitmapFont();
		heart.setColor(Color.GREEN);
		heart.getData().setScale(5);











	}

	public void CoinSetup(){

		float height=rand.nextFloat()*Gdx.graphics.getHeight();
		coinYpos.add((int)height);
		coinXpos.add(Gdx.graphics.getWidth());

	}

	public void BombSetup(){

		float height=rand2.nextFloat()*Gdx.graphics.getHeight();
		bombYpos.add((int)height);
		bombXpos.add(Gdx.graphics.getWidth());
	}


	public void DuckSetup(){

		float height=rand2.nextFloat()*Gdx.graphics.getHeight();
		duckYpos.add((int)height);
		duckXpos.add(Gdx.graphics.getWidth());
	}

	public void HeartSetup(){

		float height=rand2.nextFloat()*Gdx.graphics.getHeight();
		heartYpos.add((int)height);
		heartXpos.add(Gdx.graphics.getWidth());
	}

	public void BulletSetup(){

		float height=rand2.nextFloat()*monster.getHeight();
		bulletYpos.add((int)height);
		bulletXpos.add(Gdx.graphics.getWidth());
	}


	@Override
	public void render () {
		batch.begin();

		batch.draw(background,0,0, Gdx.graphics.getWidth(),Gdx.graphics.getHeight());



			if (score <= 12) {

				level1();
			} else if (score > 12 && score <= 30) {

				if (flag == false) {
					level_up.play();
					lives++;
					flag = true;
				}

				level2();
			} else if(score>30&&score<=65){
				if (flag == true) {

					lives++;
					flag = false;
				}
				monster_level.play();

				level3();
			}
			else{
				won.play();
				won();
			}


			bt.draw(batch, String.valueOf(score), 100, 200);

		batch.end();

	}



	public  void level1(){


		level_name.draw(batch,"LeveL 1",400,200);
		heart.draw(batch,"Lives "+ lives,400,1900);
		level=1;
		if(gameState==1){



			if(coinCount<100){
				coinCount++;
			}
			else{
				coinCount=0;
				CoinSetup();
			}
			if(bombCount<250){
				bombCount++;
			}
			else{
				bombCount=0;
				BombSetup();
			}
			coinRectangle.clear();
			for(int i=0;i<coinXpos.size();i++){

				batch.draw(coin,coinXpos.get(i),coinYpos.get(i));
				coinXpos.set(i,coinXpos.get(i)-4);
				coinRectangle.add(new Rectangle(coinXpos.get(i),coinYpos.get(i),coin.getWidth(),coin.getHeight()));
			}
			bombRectangle.clear();
			for(int i=0;i<bombXpos.size();i++){

				batch.draw(bomb,bombXpos.get(i),bombYpos.get(i));
				bombXpos.set(i,bombXpos.get(i)-8);
				bombRectangle.add(new Rectangle(bombXpos.get(i),bombYpos.get(i),bomb.getWidth(),bomb.getHeight()));
			}
			if(Gdx.input.justTouched()){





				jump.play();


				velocity=-10;
			}
			if(pausemario<6){
				pausemario++;
			}
			else {
				pausemario=0;
				if (marioPos < 3) {
					marioPos++;
				} else {
					marioPos = 0;
				}
			}
			velocity+=gravity;
			marioYheight-=velocity;
			if(marioYheight<=0){
				marioYheight=0;
			}



		}else if(gameState==0){

			if(Gdx.input.justTouched()){
				gameState=1;
			}

		}
		else if(gameState==2){
			batch.draw(dizzyMario, Gdx.graphics.getWidth()/2-mario[marioPos].getWidth()/2,marioYheight);

			if(Gdx.input.justTouched()){

				jump.play();
				lives--;
				if(lives==0){
					score=0;

				}

				gameState=1;
				//score=0;
				marioYheight=Gdx.graphics.getHeight()/2;
				velocity=0;
				coinXpos.clear();
				coinYpos.clear();
				coinRectangle.clear();
				coinCount=0;

				bombXpos.clear();
				bombYpos.clear();
				bombRectangle.clear();
				bombCount=0;

				if(lives==0){
					gameover.play();
					lives=3;
					level1();
				}

			}


		}

		batch.draw(mario[marioPos],Gdx.graphics.getWidth()/2-mario[marioPos].getWidth()/2,marioYheight);
		marioRectangle=new Rectangle(Gdx.graphics.getWidth()/2-mario[marioPos].getWidth()/2,marioYheight,mario[marioPos].getWidth(),mario[marioPos].getHeight());
		for(int i=0;i<coinRectangle.size();i++){
			if(Intersector.overlaps(marioRectangle,coinRectangle.get(i))) {




				coin_sound.play();


				score++;
				coinRectangle.remove(i);
				coinXpos.remove(i);
				coinYpos.remove(i);
				break;
			}


		}

		for(int i=0;i<bombRectangle.size();i++){
			if(Intersector.overlaps(marioRectangle,bombRectangle.get(i))){


				attack.play();

				gameState=2;


			}



		}

	}

	public void level2(){


		level_name.draw(batch,"LeveL 2",400,200);
		heart.draw(batch,"Lives "+lives,400,1900);


		if(gameState==1){



			if(coinCount<100){
				coinCount++;
			}
			else{
				coinCount=0;
				CoinSetup();
			}
			if(bombCount<400){
				bombCount++;
			}
			else{
				bombCount=0;
				BombSetup();
			}

			if(duckCount<250){
				duckCount++;
			}
			else{
				duckCount=0;
				DuckSetup();
			}

			if(heart_count<350){
				heart_count++;
			}
			else{
				heart_count=0;
				HeartSetup();
			}



			coinRectangle.clear();
			for(int i=0;i<coinXpos.size();i++){

				batch.draw(coin,coinXpos.get(i),coinYpos.get(i));
				coinXpos.set(i,coinXpos.get(i)-4);
				coinRectangle.add(new Rectangle(coinXpos.get(i),coinYpos.get(i),coin.getWidth(),coin.getHeight()));
			}
			bombRectangle.clear();
			for(int i=0;i<bombXpos.size();i++){

				batch.draw(bomb,bombXpos.get(i),bombYpos.get(i));
				bombXpos.set(i,bombXpos.get(i)-8);
				bombRectangle.add(new Rectangle(bombXpos.get(i),bombYpos.get(i),bomb.getWidth(),bomb.getHeight()));
			}

			duckRectangle.clear();
			for(int i=0;i<duckXpos.size();i++){

				batch.draw(duck,duckXpos.get(i),duckYpos.get(i));
				duckXpos.set(i,duckXpos.get(i)-12);
				duckRectangle.add(new Rectangle(duckXpos.get(i),duckYpos.get(i),duck.getWidth(),duck.getHeight()));
			}

			heartRectangle.clear();
			for(int i=0;i<heartXpos.size();i++){

				batch.draw(life,heartXpos.get(i),heartYpos.get(i));
				heartXpos.set(i,heartXpos.get(i)-16);
				heartRectangle.add(new Rectangle(heartXpos.get(i),heartYpos.get(i),life.getWidth(),life.getHeight()));
			}


			if(Gdx.input.justTouched()){

				jump.play();

				velocity=-10;
			}
			if(pausemario<6){
				pausemario++;
			}
			else {
				pausemario=0;
				if (marioPos < 3) {
					marioPos++;
				} else {
					marioPos = 0;
				}
			}
			velocity+=gravity;
			marioYheight-=velocity;
			if(marioYheight<=0){
				marioYheight=0;
			}



		}else if(gameState==0){

			if(Gdx.input.justTouched()){
				gameState=1;
			}

		}
		else if(gameState==2){
			batch.draw(dizzyMario, Gdx.graphics.getWidth()/2-mario[marioPos].getWidth()/2,marioYheight);

			if(Gdx.input.justTouched()){
				lives--;
				if(lives==0){
					score=0;
				}
				gameState=1;
				//score=0;
				marioYheight=Gdx.graphics.getHeight()/2;
				velocity=0;
				coinXpos.clear();
				coinYpos.clear();
				coinRectangle.clear();
				coinCount=0;

				bombXpos.clear();
				bombYpos.clear();
				bombRectangle.clear();
				bombCount=0;

				duckXpos.clear();
				duckYpos.clear();
				duckRectangle.clear();
				duckCount=0;

				heartXpos.clear();
				heartYpos.clear();
				heartRectangle.clear();
				heart_count=0;


				if(lives==0){
					gameover.play();
					lives=3;
					level1();
				}
				else{

					level2();
				}

			}


		}

		batch.draw(mario[marioPos],Gdx.graphics.getWidth()/2-mario[marioPos].getWidth()/2,marioYheight);
		marioRectangle=new Rectangle(Gdx.graphics.getWidth()/2-mario[marioPos].getWidth()/2,marioYheight,mario[marioPos].getWidth(),mario[marioPos].getHeight());
		for(int i=0;i<coinRectangle.size();i++){
			if(Intersector.overlaps(marioRectangle,coinRectangle.get(i))) {
				coin_sound.play();

				score++;
				coinRectangle.remove(i);
				coinXpos.remove(i);
				coinYpos.remove(i);
				break;
			}


		}

		for(int i=0;i<bombRectangle.size();i++){
			if(Intersector.overlaps(marioRectangle,bombRectangle.get(i))){
				attack.play();
				gameState=2;

			}



		}


		for(int i=0;i<duckRectangle.size();i++){
			if(Intersector.overlaps(marioRectangle,duckRectangle.get(i))){
				attack.play();
				gameState=2;

			}



		}



		for(int i=0;i<heartRectangle.size();i++){
			if(Intersector.overlaps(marioRectangle,heartRectangle.get(i))){
				level_up.play();
				lives++;
				heartXpos.remove(i);
				heartYpos.remove(i);
				heartRectangle.remove(i);
				break;

			}



		}

	}







	public void MonsterSetup(){

		float height=rand2.nextFloat()*Gdx.graphics.getHeight();
		monsterYpos.add((int)height);
		monsterXpos.add(Gdx.graphics.getWidth());
	}
	public void level3(){


		level_name.draw(batch,"LeveL 3",400,200);
		heart.draw(batch,"Lives "+String.valueOf(lives),400,1900);





		if(gameState==1){





			if(coinCount<100){
				coinCount++;
			}
			else{
				coinCount=0;
				CoinSetup();
			}

			if(bullet_count<200){
				bullet_count++;
			}
			else{
				bullet_count=0;
				BulletSetup();

			}

			if(monsterCount<200){
				monsterCount++;
			}
			else{
				monsterCount=0;
				MonsterSetup();

			}

			if(duckCount<300){
				duckCount++;
			}
			else{
				duckCount=0;
				DuckSetup();
			}

			if(heart_count<350){
				heart_count++;
			}
			else{
				heart_count=0;
				HeartSetup();
			}



			coinRectangle.clear();
			for(int i=0;i<coinXpos.size();i++){

				batch.draw(coin,coinXpos.get(i),coinYpos.get(i));
				coinXpos.set(i,coinXpos.get(i)-4);
				coinRectangle.add(new Rectangle(coinXpos.get(i),coinYpos.get(i),coin.getWidth(),coin.getHeight()));
			}



			//monsterXpos.set(i,monsterXpos.get(i)-4);



			bulletRectangle.clear();
			for(int i=0;i<bulletXpos.size();i++){

				batch.draw(bullet,bulletXpos.get(i),bulletYpos.get(i));
				bulletXpos.set(i,bulletXpos.get(i)-16);
				bulletYpos.set(i, bulletYpos.get(i));
				bulletRectangle.add(new Rectangle(bulletXpos.get(i),bulletYpos.get(i),bullet.getWidth(),bullet.getHeight()));
			}
			monsterRectangle.clear();
			for(int i=0;i<monsterXpos.size();i++){

				batch.draw(monster,monsterXpos.get(i),monsterYpos.get(i));
				monsterXpos.set(i,monsterXpos.get(i)-14);
				//monsterYpos.set(i,monsterYpos.get(i)+10);
				monsterRectangle.add(new Rectangle(monsterXpos.get(i),monsterYpos.get(i),monster.getWidth(),monster.getHeight()));
			}

			duckRectangle.clear();
			for(int i=0;i<duckXpos.size();i++){

				batch.draw(duck,duckXpos.get(i),duckYpos.get(i));
				duckXpos.set(i,duckXpos.get(i)-12);
				duckRectangle.add(new Rectangle(duckXpos.get(i),duckYpos.get(i),duck.getWidth(),duck.getHeight()));
			}

			heartRectangle.clear();
			for(int i=0;i<heartXpos.size();i++){

				batch.draw(life,heartXpos.get(i),heartYpos.get(i));
				heartXpos.set(i,heartXpos.get(i)-12);
				heartRectangle.add(new Rectangle(heartXpos.get(i),heartYpos.get(i),life.getWidth(),life.getHeight()));
			}



			if(Gdx.input.justTouched()){





				jump.play();


				velocity=-10;
			}
			if(pausemario<6){
				pausemario++;
			}
			else {
				pausemario=0;
				if (marioPos < 3) {
					marioPos++;
				} else {
					marioPos = 0;
				}
			}
			velocity+=gravity;
			marioYheight-=velocity;



			if(marioYheight<=0){
				marioYheight=0;
			}




		}else if(gameState==0){



			if(Gdx.input.justTouched()){
				gameState=1;
			}

		}
		else if(gameState==2){
			batch.draw(dizzyMario, Gdx.graphics.getWidth()/2-mario[marioPos].getWidth()/2,marioYheight);

			if(Gdx.input.justTouched()){

				jump.play();
				lives--;
				if(lives==0){
					score=0;
				}
				gameState=1;
				//score=0;
				marioYheight=Gdx.graphics.getHeight()/2;
				velocity=0;
				coinXpos.clear();
				coinYpos.clear();
				coinRectangle.clear();
				coinCount=0;


				bulletXpos.clear();
				bulletYpos.clear();
				bulletRectangle.clear();
				bullet_count=0;

				monsterXpos.clear();
				monsterYpos.clear();
				monsterRectangle.clear();
				monsterCount=0;

				duckXpos.clear();
				duckYpos.clear();
				duckRectangle.clear();
				duckCount=0;

				heartXpos.clear();
				heartYpos.clear();
				heartRectangle.clear();
				heart_count=0;

				if(lives==0){
					gameover.play();
					lives=3;
					level1();
				}
				else{

					level3();
				}





			}


		}

		batch.draw(mario[marioPos],Gdx.graphics.getWidth()/2-mario[marioPos].getWidth()/2,marioYheight);
		marioRectangle=new Rectangle(Gdx.graphics.getWidth()/2-mario[marioPos].getWidth()/2,marioYheight,mario[marioPos].getWidth(),mario[marioPos].getHeight());
		for(int i=0;i<coinRectangle.size();i++){
			if(Intersector.overlaps(marioRectangle,coinRectangle.get(i))) {




				coin_sound.play();


				score++;
				coinRectangle.remove(i);
				coinXpos.remove(i);
				coinYpos.remove(i);
				break;
			}


		}

		for(int i=0;i<bulletRectangle.size();i++){
			if(Intersector.overlaps(marioRectangle,bulletRectangle.get(i))){


				attack.play();

				gameState=2;


			}



		}

		for(int i=0;i<duckRectangle.size();i++){
			if(Intersector.overlaps(marioRectangle,duckRectangle.get(i))){
				attack.play();
				gameState=2;

			}



		}

		for(int i=0;i<monsterRectangle.size();i++){
			if(Intersector.overlaps(marioRectangle,monsterRectangle.get(i))){


				attack.play();

				gameState=2;


			}



		}


		for(int i=0;i<heartRectangle.size();i++) {
			if (Intersector.overlaps(marioRectangle, heartRectangle.get(i))) {
				level_up.play();
				lives++;
				heartXpos.remove(i);
				heartYpos.remove(i);
				heartRectangle.remove(i);
				break;

			}
		}


	}





	public void won(){

		level_name.draw(batch,"You Won",400,700);
		batch.draw(kingdom,Gdx.graphics.getWidth()/2-200,Gdx.graphics.getHeight()/2-400);
		batch.draw(mario[0],Gdx.graphics.getWidth()/2-mario[0].getWidth()/2,marioYheight);

		Timer.schedule(new Timer.Task(){
			@Override
			public void run() {
				// Do your work

			}
		}, 4);

		if(Gdx.input.justTouched()){

			exitcount++;

			if(exitcount>=2) {

				Gdx.app.exit();
			}
		}





	}

	@Override
	public void dispose () {
		batch.dispose();

	}


}
