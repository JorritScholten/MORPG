# MORPG (Miniature Offline Role Playing Game) project

Make a game with a player.

## Tasks

-[ ] Implement the player and the orc. Hint: you could make a class "Character", and create one of each in the main program
  -[ ] The player is has 50 hitpoints
  -[ ] There is an orc, which has 20 hitpoints
-[ ] We want them to be able to fight: we equip both of them with a weapon. Give the player a sword which does 1d6 (1/2/3/4/5/6) points of damage each round, and the orc an axe which does 1d3 damage each round (1/2/3). Hint: you could make a 'weapon' class. Whether you subclass it to Axe and Sword subclasses is up to you. Make a method "hit" in the Player/Character/class that takes a target as argument. Like: player.hits(orc), the target taking damage dependent on the other's weapon. You could also add a 'getWounded' method.
-[ ] Of course, we also want to SEE (ehm... read) some action: add code to display when someone does damage or gets wounded (like 'Player swings his sword and does 6 damage to orc' / 'orc gets hit by Player and drops to 14 hitpoints' Now let the player hit the orc. Then let the orc hit the player.
-[ ] Of course, as things stand, player and orc can keep bashing each other into infinity. Make it so that when any character takes damage that reduces them to (or below) 0 hit points, a message is shows that the character dies. Any loop can end by checking the "isAlive()" property. Also note that a dead character cannot hit back.

## How to run on Linux
