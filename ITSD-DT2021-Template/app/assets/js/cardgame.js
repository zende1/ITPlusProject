function initHexi(preloadImages) {
	
	//1. Setting up and starting Hexi

	//Initialize and start Hexi
	g = hexi(stageWidth, stageHeight, setup, preloadImages, load);
	g.fps = 60;
	g.border = "2px red dashed";
	g.backgroundColor = 0x000000;
	g.scaleToWindow();
	g.start();
	
}

//2. The `load` function that will run while your files are loading

function load(){
  
  //Display the file currently being loaded
  console.log(`loading: ${g.loadingFile}`); 

  //Display the percentage of files currently loaded
  console.log(`progress: ${g.loadingProgress}`);

  //Add an optional loading bar 
  g.loadingBar();
}

//3. The `setup` function, which initializes your game objects, variables and sprites

function setup() {

  //Create your game objects here
  let bg = g.sprite("assets/game/extra/battlemap6_middleground.png");
  bg.interactive = true;
  bg.on('click', bgClicked);
  bg.setPosition(0, 0);
  bg.width = stageWidth;
  bg.height = stageHeight;
  g.stage.addChild(bg);

  // Set up Methods for triggering actions based on pointer clicks
  //g.pointer.press = () => {console.log("The pointer was pressed "+g.pointer.x+" "+g.pointer.y);}

  //Add a custom `release` method
  //g.pointer.release = () => {console.log("The pointer was released");}

  //Add a custom `tap` method
  //g.pointer.tap = () => {console.log("The pointer was tapped");}

  //Set the game state to `play` to start the game loop
  g.state = play;
}

function bgClicked(eventData) {
	ws.send(JSON.stringify({
    		messagetype: "otherclicked"
  	}));
}

function drawTile(message) {
	
	var tileid = message.tile.tilex+"-"+message.tile.tiley;
	
	if (boardTiles.has(tileid)) {
		var tile = boardTiles.get(tileid);
		tile.show(message.mode);
		
	} else {
		var tile = g.sprite(message.tile.tileTextures);
    	tile.setPosition(message.tile.xpos, message.tile.ypos);
    	tile.width = message.tile.width;
    	tile.height = message.tile.height;
		tile.tilex = message.tile.tilex;
		tile.tiley = message.tile.tiley;
		tile.show(message.mode);
		tile.interactive = true;
		tile.on('click', tileClicked);
    	g.stage.addChild(tile);

		boardTiles.set(tileid, tile);
	}
	
	//console.log(message);
	
}

function tileClicked(eventData) {
	ws.send(JSON.stringify({
    		messagetype: "tileclicked",
            tilex: eventData.target.tilex,
            tiley: eventData.target.tiley,
  	}));
}

function drawCard(message) {
	
	var handIndex = message.position-1; // correct for indices starting from 0
	
	if (handContainers[handIndex]!=null) {
		// delete the container before drawing the new one
		g.stage.removeChild(handContainers[handIndex]);
		handContainers[handIndex] = null;
	}
	
	var cardContainer = new PIXI.Container();
	
	cardJSON[handIndex] = message.card;
	
	var backgroundCardImage = g.sprite(message.card.miniCard.cardTextures);
	backgroundCardImage.show(message.mode);
	backgroundCardImage.setPosition(0, 0);
    backgroundCardImage.width = 200;
    backgroundCardImage.height = 200;
	backgroundCardImage.cardindex = handIndex+1;
	backgroundCardImage.interactive = true;
	backgroundCardImage.on('click', cardClicked);
	cardContainer.addChild(backgroundCardImage);
	
	var cardSprite = g.sprite(message.card.miniCard.animationFrames);
	
	if (message.mode==0) {
		cardSprite.show(message.card.miniCard.index);
	} else {
		cardSprite.playAnimation();
	}
	
	cardSprite.setPosition(50, 50);
    cardSprite.width = 100;
    cardSprite.height = 100;
	cardContainer.addChild(cardSprite);
	handSprites[handIndex] = cardSprite;
	
	var cardNameBackground = g.sprite("assets/game/extra/ui/button_end_turn_enemy.png");
	cardNameBackground.setPosition(10, 140);
    cardNameBackground.width = 180;
    cardNameBackground.height = 50;
	cardContainer.addChild(cardNameBackground);
	
	var cardText = new PIXI.Text(message.card.cardname, { font: '20px Roboto', fill: 'white', align: 'center' });
	cardText.position.x = 35;
	cardText.position.y = 155;
	cardContainer.addChild(cardText);
	
	var manacircle = g.sprite("assets/game/extra/ManaCircle.png");
    manacircle.setPosition(120, 15);
    manacircle.width = 50;
    manacircle.height = 50;
	cardContainer.addChild(manacircle);
	
	var manaText = new PIXI.Text(message.card.manacost, { font: '25px Roboto', fill: 'white', align: 'center' });
	manaText.position.x = 138;
	manaText.position.y = 25;
	cardContainer.addChild(manaText);
	handContainers[handIndex] = cardContainer;
	
	
	cardContainer.position.x = 400+(handIndex*220);
	cardContainer.position.y = 880;

	g.stage.addChild(cardContainer);
}

function renderCardPreview(position) {
	
	if (cardPreview!=null) {
		g.stage.removeChild(cardPreview);
		cardPreview = null;
	}
	
	var card = cardJSON[position-1];
	
	var previewContainer = new PIXI.Container();
	
	
	var backgroundCardImage = g.sprite(card.bigCard.cardTextures);
	backgroundCardImage.show(0);
	backgroundCardImage.setPosition(0, 0);
    backgroundCardImage.width = 250;
    backgroundCardImage.height = 350;
	previewContainer.addChild(backgroundCardImage);
	
	var cardText = new PIXI.Text(card.cardname, { font: '20px Roboto', fill: 'white', align: 'center' });
	cardText.position.x = 30;
	cardText.position.y = 30;
	previewContainer.addChild(cardText);
	
	var cardSprite = g.sprite(card.miniCard.animationFrames);
	cardSprite.playAnimation();
	cardSprite.setPosition(75, 65);
    cardSprite.width = 100;
    cardSprite.height = 100;
	previewContainer.addChild(cardSprite);
	
	var manacircle = g.sprite("assets/game/extra/ManaCircle.png");
    manacircle.setPosition(190, 15);
    manacircle.width = 50;
    manacircle.height = 50;
	previewContainer.addChild(manacircle);
	
	var manaText = new PIXI.Text(card.manacost, { font: '25px Roboto', fill: 'white', align: 'center' });
	manaText.position.x = 210;
	manaText.position.y = 25;
	previewContainer.addChild(manaText);
	
	for (i = 0; i < card.bigCard.rulesTextRows.length; i++) {
		var line = card.bigCard.rulesTextRows[i];
		var rulesLine = new PIXI.Text(line, { font: '15px Roboto', fill: 'white', align: 'center' });
		rulesLine.position.x = 30;
		rulesLine.position.y = 250+(i*20);
		previewContainer.addChild(rulesLine);
	}
	
	if (card.bigCard.attack>-1) {
		var cardAttack = new PIXI.Text(card.bigCard.attack, { font: '20px Roboto', fill: 'white', align: 'center' });
		cardAttack.position.x = 60;
		cardAttack.position.y = 200;
		previewContainer.addChild(cardAttack);
		
		var cardHealth = new PIXI.Text(card.bigCard.health, { font: '20px Roboto', fill: 'white', align: 'center' });
		cardHealth.position.x = 180;
		cardHealth.position.y = 200;
		previewContainer.addChild(cardHealth);
	}
	
	previewContainer.position.x = 1600;
	previewContainer.position.y = 400;
	
	
	
	prevewCountdown = 300;
	g.stage.addChild(previewContainer);
	cardPreview = previewContainer;
}

function cardClicked(eventData) {
	renderCardPreview(eventData.target.cardindex);
	ws.send(JSON.stringify({
    		messagetype: "cardclicked",
            position: eventData.target.cardindex
  	}));
}


function drawUnit(message) {
	
	//console.log(message.unit);

	var unitContainer = new PIXI.Container();
	
	// Draw unit in idle stance
	var unit = g.sprite(message.unit.animations.allFrames);
	unit.playAnimation(message.unit.animations.idle.frameStartEndIndices);
	unit.loop = message.unit.animations.idle.loop;
	unit.fps = message.unit.animations.idle.fps;
	
	var spriteX = message.unit.position.xpos - message.unit.correction.spriteTopLeftX;
	var spriteY = message.unit.position.ypos - message.unit.correction.spriteTopLeftY-20;
	
	unit.setPosition(message.unit.correction.offsetX, message.unit.correction.offsetY);
    unit.width = message.unit.correction.imgWidth*(1+(message.unit.correction.spriteTopLeftX/message.unit.correction.imgWidth))*message.unit.correction.scale;
    unit.height = message.unit.correction.imgHeight*(1+(message.unit.correction.spriteTopLeftY/message.unit.correction.imgHeight))*message.unit.correction.scale;
	unit.gameID = message.unit.id;
	
	// if reflect, flip the unit sprite
	if (message.unit.correction.reflected) {
		unit.scale.x = -1*message.unit.correction.scale;
		unit.setPosition((message.unit.correction.imgWidth*message.unit.correction.scale)+message.unit.correction.offsetX, message.unit.correction.offsetY);
	}
	
	//unit.interactive = true;
	//unit.on('click', unitClicked);
    unitContainer.addChild(unit);

	// Draw attack value
	var attackcircle = g.sprite("assets/game/extra/AttackCircle.png");
    attackcircle.setPosition(message.unit.correction.spriteTopLeftX+5, message.unit.correction.spriteTopLeftY+message.tile.height-25);
    attackcircle.width = 40;
    attackcircle.height = 40;
	unitContainer.addChild(attackcircle);
	
	var attackText = new PIXI.Text('0', { font: '20px Roboto', fill: 'white', align: 'center' });
	attackText.position.x = message.unit.correction.spriteTopLeftX+20;
	attackText.position.y = message.unit.correction.spriteTopLeftY+message.tile.height-15;
	unitContainer.addChild(attackText);
	
	// Draw health value
	var healthcircle = g.sprite("assets/game/extra/HealthCircle.png");
    healthcircle.setPosition(message.unit.correction.spriteTopLeftX+message.tile.height-45, message.unit.correction.spriteTopLeftY+message.tile.height-25);
    healthcircle.width = 40;
    healthcircle.height = 40;
	unitContainer.addChild(healthcircle);
	
	var healthText = new PIXI.Text('0', { font: '20px Roboto', fill: 'white', align: 'center' });
	healthText.position.x = message.unit.correction.spriteTopLeftX+message.tile.height-30;
	healthText.position.y = message.unit.correction.spriteTopLeftY+message.tile.height-15;
	unitContainer.addChild(healthText);

	unitContainer.position.x = spriteX;
	unitContainer.position.y = spriteY;

	g.stage.addChild(unitContainer);

	spriteContainers.set(message.unit.id, unitContainer);
	sprites.set(message.unit.id, unit);
	healthLabels.set(message.unit.id, healthText);
	attackLabels.set(message.unit.id, attackText);
	
	
	

}


function getFrameSet(unit) {
	var frameSet = [];
	var anim = unit.animation;
	
	if (anim === "idle") {
		frameSet = unit.animations.idle;
	}
	if (anim === "death") {
		frameSet = unit.animations.death;
	}
	if (anim === "attack") {
		frameSet = unit.animations.attack;
	}
	if (anim === "move") {
		frameSet = unit.animations.move;
	}
	if (anim === "channel") {
		frameSet = unit.animations.channel;
	}
	if (anim === "hit") {
		frameSet = unit.animations.hit;
	}
	
	console.log(frameSet);
	
	return frameSet;
}

// Starts a move action for a Unit
function moveUnit(unitID, xTile, yTile) {
	ws.send(JSON.stringify({
    		messagetype: "getTileForMove",
			unitID: unitID,
			xTile: xTile,
			yTile: yTile
  		}));
}

function moveUnitToTile(message) {
	
	activeMoves.set(message.unitID, message);
}

// Performs a single frame move towards the target destination for a sprite
// Returns whether the destination has been reached
function executeMoveStep(message) {
	
	var targetUnit = sprites.get(message.unit.id);
	var targetContainer = spriteContainers.get(message.unit.id);
	
	if (message.unit.animation != "move") {
		targetUnit.stopAnimation();
		
		ws.send(JSON.stringify({
    		messagetype: "unitMoving",
			id: message.unit.id
  		}));
		
		message.unit.animation = "move";
		targetUnit.fps = message.unit.animations.move.fps;
		targetUnit.loop = message.unit.animations.move.loop;
		targetUnit.playAnimation(message.unit.animations.move.frameStartEndIndices);
		//sprite.interactive = false;
	}
	
	
	//console.log(moveMessage)
	
	var spriteX = message.tile.xpos - message.unit.correction.spriteTopLeftX;
	var spriteY = message.tile.ypos - message.unit.correction.spriteTopLeftY-20;
	
	var dx = Math.abs(targetContainer.position.x - spriteX);
    var dy = Math.abs(targetContainer.position.y - spriteY);

	//console.log("d:"+dx+" "+dy);

    if ((dx+dy) > 0) {
        
		//console.log(targetContainer.position.x+" "+targetContainer.position.y);

		if (dx>0) {
			if (targetContainer.position.x - spriteX < 0) {
				targetContainer.position.vx = Math.min(moveVelocity, dx);
				targetContainer.position.vy = 0;
			} else {
				targetContainer.position.vx = Math.max(-moveVelocity, -dx);
				targetContainer.position.vy = 0;
			}
		} else {
			if (dy>0) {
				if (targetContainer.position.y - spriteY < 0) {
					targetContainer.position.vy = Math.min(moveVelocity,dy);
					targetContainer.position.vx = 0;
				}else {
					targetContainer.position.vy = Math.max(-moveVelocity,-dy);
					targetContainer.position.vx = 0;
				}
			}
		}

		g.move(targetContainer.position);
		
		dx = Math.abs(targetContainer.position.x - spriteX);
		dy = Math.abs(targetContainer.position.y - spriteY);
		
		return false
    } else {

	  var sprite = sprites.get(message.unit.id);
	  sprite.stopAnimation();

	  ws.send(JSON.stringify({
    		messagetype: "unitstopped",
			id: message.unit.id,
			tilex: message.tile.tilex,
			tiley: message.tile.tiley
  	  }));

	  message.unit.animation = "idle";
	  targetUnit.fps = message.unit.animations.idle.fps;
      targetUnit.loop = message.unit.animations.idle.loop;
	  targetUnit.playAnimation(message.unit.animations.idle.frameStartEndIndices);

      return true;
    }
}

function drawProjectile(message) {
	
	var projectile = g.sprite(message.effect.animationTextures);
	var effectX = message.tile.xpos - message.effect.correction.spriteTopLeftX;
	var effectY = message.tile.ypos - message.effect.correction.spriteTopLeftY;
	projectile.setPosition(effectX+message.effect.correction.offsetX, effectY+message.effect.correction.offsetY);
    projectile.width = message.effect.correction.imgWidth*(1+(message.effect.correction.spriteTopLeftX/message.effect.correction.imgWidth))*message.effect.correction.scale;
    projectile.height = message.effect.correction.imgHeight*(1+(message.effect.correction.spriteTopLeftY/message.effect.correction.imgHeight))*message.effect.correction.scale;
	projectile.show(message.mode);
	
	// if reflect, flip the unit sprite
	if (message.effect.correction.reflected) {
		projectile.scale.x = -1*message.effect.correction.scale;
		projectile.setPosition((message.effect.correction.imgWidth*message.effect.correction.scale)+message.effect.correction.offsetX, message.effect.correction.offsetY);
	}
	
	projectile.tile = message.tile;
	projectile.targetTile = message.targetTile;
	projectile.effect = message.effect;
	
	g.stage.addChild(projectile);
	
	activeProjectiles.push(projectile)

}

function executeProjectileMoveStep(projectile) {
	
	
	var tile = projectile.tile;
	var targetTile = projectile.targetTile;
	var effect = projectile.effect;
	
	var xvelocity = Math.abs(tile.tilex-targetTile.tilex)*2;
	var yvelocity = Math.abs(tile.tiley-targetTile.tiley)*2;
	
	var spriteX = targetTile.xpos - effect.correction.spriteTopLeftX+effect.correction.offsetX;
	var spriteY = targetTile.ypos - effect.correction.spriteTopLeftY+effect.correction.offsetY;
	
	var dx = Math.abs(projectile.position.x - spriteX);
    var dy = Math.abs(projectile.position.y - spriteY);

	//console.log("d:"+dx+" "+dy);

    if ((dx+dy) > 0) {
        
		//console.log(targetContainer.position.x+" "+targetContainer.position.y);

		if (dx>0) {
			if (projectile.position.x - spriteX < 0) {
				projectile.position.vx = Math.min(xvelocity, dx);
			} else {
				projectile.position.vx = Math.max(-xvelocity, -dx);
			}
		} else {
			projectile.position.vx = 0;
		}
		if (dy>0) {
			if (projectile.position.y - spriteY < 0) {
				projectile.position.vy = Math.min(yvelocity,dy);
			}else {
				projectile.position.vy = Math.max(-yvelocity,-dy);
			}
		} else {
			projectile.position.vy = 0;
		}

		g.move(projectile.position);
		
		dx = Math.abs(projectile.position.x - spriteX);
		dy = Math.abs(projectile.position.y - spriteY);
		
		return false
    } else {
		g.stage.removeChild(projectile);
		return true;
	}
}


function setUnitHealth(message) {
	var unitID = message.unit.id;
	var health = message.health;
	
	
	var oldHealth = parseInt(healthLabels.get(unitID).text);
	healthLabels.get(unitID).text = health;
	
	if (health>9 && oldHealth<10) {
		healthLabels.get(unitID).position.x = healthLabels.get(unitID).position.x-5;
	} else if (health<10 && oldHealth>9) {
		healthLabels.get(unitID).position.x = healthLabels.get(unitID).position.x+5;
	}
	
}

function setUnitAttack(message) {
	var unitID = message.unit.id;
	var attack = message.attack;
	
	
	var oldAttack = parseInt(attackLabels.get(unitID).text);
	attackLabels.get(unitID).text = attack;
	
	if (attack>9 && oldAttack<10) {
		attackLabels.get(unitID).position.x = attackLabels.get(unitID).position.x-5;
	} else if (attack<10 && oldAttack>9) {
		attackLabels.get(unitID).position.x = attackLabels.get(unitID).position.x+5;
	}
	
}


function renderPlayer1Card() {
	
	var icons = ["assets/game/extra/ui/icon_mana_inactive.png","assets/game/extra/ui/icon_mana.png"];
	
	var health = g.sprite("assets/game/extra/ui/notification_quest_small.png");
    health.setPosition(30, 250);
    health.width = 350;
    health.height = 110;
	g.stage.addChild(health);
	
	var healthTitle = new PIXI.Text('Life', { font: '28px Roboto', fill: 'white', align: 'center' });
	healthTitle.position.x = 120;
	healthTitle.position.y = 285;
	g.stage.addChild(healthTitle);
	
	player1Health = new PIXI.Text('0', { font: '28px Roboto', fill: 'white', align: 'center' });
	player1Health.position.x = 280;
	player1Health.position.y = 285;
	g.stage.addChild(player1Health);
	
	
	var mana1 = g.sprite(icons);
    mana1.setPosition(60, 400);
    mana1.width = 80;
    mana1.height = 80;
	mana1.show(0);
	g.stage.addChild(mana1);
	player1ManaIcons.set(1, mana1);
	
	var mana2 = g.sprite(icons);
    mana2.setPosition(60+50, 450);
    mana2.width = 80;
    mana2.height = 80;
	mana2.show(0);
	g.stage.addChild(mana2);
	player1ManaIcons.set(2, mana2);
	
	var mana3 = g.sprite(icons);
    mana3.setPosition(60, 500);
    mana3.width = 80;
    mana3.height = 80;
	mana3.show(0);
	g.stage.addChild(mana3);
	player1ManaIcons.set(3, mana3);
	
	var mana4 = g.sprite(icons);
    mana4.setPosition(60+50, 550);
    mana4.width = 80;
    mana4.height = 80;
	mana4.show(0);
	g.stage.addChild(mana4);
	player1ManaIcons.set(4, mana4);
	
	var mana5 = g.sprite(icons);
    mana5.setPosition(60, 600);
    mana5.width = 80;
    mana5.height = 80;
	mana5.show(0);
	g.stage.addChild(mana5);
	player1ManaIcons.set(5, mana5);
	
	var mana6 = g.sprite(icons);
    mana6.setPosition(60+50, 650);
    mana6.width = 80;
    mana6.height = 80;
	mana6.show(0);
	g.stage.addChild(mana6);
	player1ManaIcons.set(6, mana6);
	
	var mana7 = g.sprite(icons);
    mana7.setPosition(60, 700);
    mana7.width = 80;
    mana7.height = 80;
	mana7.show(0);
	g.stage.addChild(mana7);
	player1ManaIcons.set(7, mana7);
	
	var mana8 = g.sprite(icons);
    mana8.setPosition(60+50, 750);
    mana8.width = 80;
    mana8.height = 80;
	mana8.show(0);
	g.stage.addChild(mana8);
	player1ManaIcons.set(8, mana8);
	
	var mana9 = g.sprite(icons);
    mana9.setPosition(60, 800);
    mana9.width = 80;
    mana9.height = 80;
	mana9.show(0);
	g.stage.addChild(mana9);
	player1ManaIcons.set(9, mana9);
	
	var player1Portrait = g.sprite("assets/game/extra/ui/general_portrait_image_hex_f1-third@2x.png");
    player1Portrait.setPosition(65, 20);
    player1Portrait.width = 300;
    player1Portrait.height = 300;
	g.stage.addChild(player1Portrait);
	
	
	
}

function renderEndTurnButton() {
	var endTurnButton = g.sprite("assets/game/extra/ui/button_primary.png");
    endTurnButton.setPosition(1600, 950);
    endTurnButton.width = 300;
    endTurnButton.height = 100;
	endTurnButton.on('click', endturnClicked);
	endTurnButton.interactive = true;
	g.stage.addChild(endTurnButton);
	
	var endTurnText = new PIXI.Text('End Turn', { font: '28px Roboto', fill: 'white', align: 'center' });
	endTurnText.position.x = 1700;
	endTurnText.position.y = 980;
	g.stage.addChild(endTurnText);
	
}

function endturnClicked(eventData) {
	ws.send(JSON.stringify({
    		messagetype: "endturnclicked"
  	}));
}


function renderPlayer2Card() {

	var moveRight = 1500;
	var icons = ["assets/game/extra/ui/icon_mana_inactive.png","assets/game/extra/ui/icon_mana.png"];


	var health = g.sprite("assets/game/extra/ui/notification_quest_small.png");
    health.setPosition(moveRight+30, 250);
    health.width = 350;
    health.height = 110;
	g.stage.addChild(health);
	
	var healthTitle = new PIXI.Text('Life', { font: '28px Roboto', fill: 'white', align: 'center' });
	healthTitle.position.x = moveRight+120;
	healthTitle.position.y = 285;
	g.stage.addChild(healthTitle);
	
	player2Health = new PIXI.Text('0', { font: '28px Roboto', fill: 'white', align: 'center' });
	player2Health.position.x = moveRight+280;
	player2Health.position.y = 285;
	g.stage.addChild(player2Health);
	
	
	var mana1 = g.sprite(icons);
    mana1.setPosition(moveRight+200, 400);
    mana1.width = 80;
    mana1.height = 80;
	mana1.show(0);
	g.stage.addChild(mana1);
	player2ManaIcons.set(1, mana1);
	
	var mana2 = g.sprite(icons);
    mana2.setPosition(moveRight+200+50, 450);
    mana2.width = 80;
    mana2.height = 80;
	mana2.show(0);
	g.stage.addChild(mana2);
	player2ManaIcons.set(2, mana2);
	
	var mana3 = g.sprite(icons);
    mana3.setPosition(moveRight+200, 500);
    mana3.width = 80;
    mana3.height = 80;
	mana3.show(0);
	g.stage.addChild(mana3);
	player2ManaIcons.set(3, mana3);
	
	var mana4 = g.sprite(icons);
    mana4.setPosition(moveRight+200+50, 550);
    mana4.width = 80;
    mana4.height = 80;
	mana4.show(0);
	g.stage.addChild(mana4);
	player2ManaIcons.set(4, mana4);
	
	var mana5 = g.sprite(icons);
    mana5.setPosition(moveRight+200, 600);
    mana5.width = 80;
    mana5.height = 80;
	mana5.show(0);
	g.stage.addChild(mana5);
	player2ManaIcons.set(5, mana5);
	
	var mana6 = g.sprite(icons);
    mana6.setPosition(moveRight+200+50, 650);
    mana6.width = 80;
    mana6.height = 80;
	mana6.show(0);
	g.stage.addChild(mana6);
	player2ManaIcons.set(6, mana6);
	
	var mana7 = g.sprite(icons);
    mana7.setPosition(moveRight+200, 700);
    mana7.width = 80;
    mana7.height = 80;
	mana7.show(0);
	g.stage.addChild(mana7);
	player2ManaIcons.set(7, mana7);
	
	var mana8 = g.sprite(icons);
    mana8.setPosition(moveRight+200+50, 750);
    mana8.width = 80;
    mana8.height = 80;
	mana8.show(0);
	g.stage.addChild(mana8);
	player2ManaIcons.set(8, mana8);
	
	var mana9 = g.sprite(icons);
    mana9.setPosition(moveRight+200, 800);
    mana9.width = 80;
    mana9.height = 80;
	mana9.show(0);
	g.stage.addChild(mana9);
	player2ManaIcons.set(9, mana9);
	
	var player2Portrait = g.sprite("assets/game/extra/ui/general_portrait_image_hex_f3@2x.png");
    player2Portrait.setPosition(moveRight+65, 20);
    player2Portrait.width = 300;
    player2Portrait.height = 300;
	g.stage.addChild(player2Portrait);
	
}


function setPlayer1Health(message) {
	player1Health.text = message.player.health;
}

function setPlayer2Health(message) {
	player2Health.text = message.player.health;
}

function setPlayer1Mana(message) {
	
	var mana = message.player.mana;
	
	for (i = 1; i < 10; i++) {
		if (mana>=i) player1ManaIcons.get(i).show(1);
		else player1ManaIcons.get(i).show(0);
	}	
}

function setPlayer2Mana(message) {
	
	var mana = message.player.mana;
	
	for (i = 1; i < 10; i++) {
		if (mana>=i) player2ManaIcons.get(i).show(1);
		else player2ManaIcons.get(i).show(0);
	}	
}

function addPlayer1Notification(message) {
	if (player1Notification==null) {
		// we need to create a new notification
		
		player1Notification = g.sprite("assets/game/extra/ui/tooltip_left@2x.png");
    	player1Notification.setPosition(320, 100);
    	player1Notification.width = 400;
    	player1Notification.height = 150;
		player1Notification.countdown = message.seconds*60;
		g.stage.addChild(player1Notification);
		
		player1NotificationText = new PIXI.Text(message.text, { font: '40px Roboto', fill: 'white', align: 'center' });
		player1NotificationText.position.x = 400;
		player1NotificationText.position.y = 150;
		g.stage.addChild(player1NotificationText);
	} else {
		player1Notification.countdown = message.seconds*60;
		player1NotificationText.text = message.text;
	}
}

function addPlayer2Notification(message) {
	if (player2Notification==null) {
		// we need to create a new notification
		
		player2Notification = g.sprite("assets/game/extra/ui/tooltip_right@2x.png");
    	player2Notification.setPosition(320, 100);
    	player2Notification.width = 400;
    	player2Notification.height = 150;
		player2Notification.countdown = message.seconds*60;
		g.stage.addChild(player2Notification);
		
		player2NotificationText = new PIXI.Text(message.text, { font: '40px Roboto', fill: 'white', align: 'center' });
		player2NotificationText.position.x = 400;
		player2NotificationText.position.y = 150;
		g.stage.addChild(player2NotificationText);
	} else {
		player2Notification.countdown = message.seconds*60;
		player2NotificationText.text = message.text;
	}
}


function playUnitAnimation(message) {
	
	var targetUnit = sprites.get(message.unit.id);
	var animationData = getFrameSet(message.unit);
	targetUnit.loop = animationData.loop;
	targetUnit.fps = animationData.fps;
	targetUnit.playAnimation(animationData.frameStartEndIndices);
	
	
}

function deleteCard(message) {
	var card = handContainers[message.position-1];
	g.stage.removeChild(card);
	handContainers[message.position]=null;
	handSprites[message.position]=null;
	cardJSON[message.position]=null;
	prevewCountdown = 0;
}

function deleteUnit(message) {
	var unitContainer = spriteContainers.get(message.unit.id);
	g.stage.removeChild(unitContainer);
	spriteContainers.delete(message.unit.id);
	sprites.delete(message.unit.id);
	attackLabels.delete(message.unit.id);
	healthLabels.delete(message.unit.id);
}


function playEffectAnimation(message) {
	
	var effect = g.sprite(message.effect.animationTextures);
	var effectX = message.tile.xpos - message.effect.correction.spriteTopLeftX;
	var effectY = message.tile.ypos - message.effect.correction.spriteTopLeftY;
	effect.setPosition(effectX+message.effect.correction.offsetX, effectY+message.effect.correction.offsetY);
    effect.width = message.effect.correction.imgWidth*(1+(message.effect.correction.spriteTopLeftX/message.effect.correction.imgWidth))*message.effect.correction.scale;
    effect.height = message.effect.correction.imgHeight*(1+(message.effect.correction.spriteTopLeftY/message.effect.correction.imgHeight))*message.effect.correction.scale;
	effect.fps = message.effect.fps;
	effect.loop = false;
	effect.playAnimation();
	g.stage.addChild(effect);
	
	var frameDiff = (60/effect.fps)+1;
	effect.killCountdown = frameDiff*message.effect.animationTextures.length;
	
	console.log(effect);
	
	playingEffects.push(effect);
}


//4. The `play` function, which is your game or application logic that runs in a loop

function play(){
  //This is your game loop, where you can move sprites and add your
  //game logic

  if (gameActorInitalized) {
	
	if (!gameStart) {
		ws.send(JSON.stringify({
    		messagetype: "initalize"
  		}));
        gameStart = true;

		renderPlayer1Card();
		renderPlayer2Card();
		renderEndTurnButton();
	}
	
	// Draw Tile Actions
	while (drawTileQueue.length>0) {
		drawTile(drawTileQueue.pop());
	}
	
	// Draw Tile Actions
	while (drawUnitQueue.length>0) {
		drawUnit(drawUnitQueue.pop());
	}
	
	var continuingProjectiles = [];
	for (i = 0; i < activeProjectiles.length; i++) {
		if(!executeProjectileMoveStep(activeProjectiles[i])) {
			continuingProjectiles.push(activeProjectiles[i]);
		}
	}
	activeProjectiles = continuingProjectiles;
		
	
	// Operationalize Sprite Movement
    var completedMoves = [];

    for (let [key, value] of activeMoves) {
	  if (executeMoveStep(value)) {
	  	  completedMoves.push(key);
	  }
    }

    for (i = 0; i < completedMoves.length; i++) {
      activeMoves.delete(completedMoves[i]);
    }

	if (player1Notification!==null) {
		player1Notification.countdown = player1Notification.countdown-1;
		if (player1Notification.countdown<=0) {
			g.stage.removeChild(player1Notification);
			g.stage.removeChild(player1NotificationText);
			player1Notification = null;
			player1NotificationText = null;
		}
	}
	
	if (player2Notification!==null) {
		player2Notification.countdown = player2Notification.countdown-1;
		if (player2Notification.countdown<=0) {
			g.stage.removeChild(player2Notification);
			g.stage.removeChild(player2NotificationText);
			player2Notification = null;
			player2NotificationText = null;
		}
	}
	
	// Remove the card preview after the pre-determined time
	if (cardPreview!=null) {
		
		if (prevewCountdown<=0) {
			g.stage.removeChild(cardPreview);
			cardPreview = null;
		}
		prevewCountdown = prevewCountdown -1;
		
	}
	
	// tidy up animations that have finished playing
	for (i = 0; i < playingEffects.length; i++) {
		if (playingEffects[i].killCountdown<=0) {
			g.stage.removeChild(playingEffects[i]);
			playingEffects.splice(i, 1);
			break;
		}
		playingEffects[i].killCountdown = playingEffects[i].killCountdown-1;
    }
	

	sinceLastHeartbeat = sinceLastHeartbeat+1;
	if (sinceLastHeartbeat==120) {
		ws.send(JSON.stringify({
    		messagetype: "heartbeat"
        }));
        sinceLastHeartbeat = 1;
	}
    				
  }

  

}