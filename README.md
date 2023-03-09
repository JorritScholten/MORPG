# MORPG (Miniature Offline Role Playing Game) project

Make a game with a player.

## Tasks

- [x] Configure project so that a one-click build and launch works.
  - [x] ~~Create class to provide an executable.~~
- [ ] Implement the player and the orc. Hint: you could make a class "Character", and create one of each in the main
  program.
  - [ ] The player is has 50 hitpoints
  - [ ] There is an orc, which has 20 hitpoints
- [ ] We want them to be able to fight: we equip both of them with a weapon. Give the player a sword which does 1d6 (
  1/2/3/4/5/6) points of damage each round, and the orc an axe which does 1d3 damage each round (1/2/3). Hint: you could
  make a 'weapon' class. Whether you subclass it to Axe and Sword subclasses is up to you. Make a method "hit" in the
  Player/Character/class that takes a target as argument. Like: player.hits(orc), the target taking damage dependent on
  the other's weapon. You could also add a 'getWounded' method.
- [ ] Of course, we also want to SEE (ehm... read) some action: add code to display when someone does damage or gets
  wounded (like 'Player swings his sword and does 6 damage to orc' / 'orc gets hit by Player and drops to 14 hitpoints'
  Now let the player hit the orc. Then let the orc hit the player.
- [ ] Of course, as things stand, player and orc can keep bashing each other into infinity. Make it so that when any
  character takes damage that reduces them to (or below) 0 hit points, a message is shows that the character dies. Any
  loop can end by checking the "isAlive()" property. Also note that a dead character cannot hit back.

you should now have at least two classes and about 50 lines of code.
So far you have perhaps seen the usefulness of bundling data with methods, but clearly there was little true OO
behavior: after all, Player and orc only differed in field values (hit points, weapon type, and name) but not in
behavior. However, you may already see some reasons for that to change, for example that
"Player gets hit by orc and drops to 37 hit points!" and "orc hits Player with his axe for 1 hit points!" is not
really proper English. But we can fix that!

- [ ] Let's first make everything proper English. Obviously, there are player characters with names (that start with a
  capital letter) and monster characters that only have a species (they of course may have names for their friends and
  family, but we dungeoneering heroes don't care about that stuff). Let's make two subclasses of Character, namely
  Player
  and Creature. Then we will make Character abstract, to ensure only a useful being can be made.
- [ ] This is better. It would be nice though if all sentences would start with capital letters. Let's make a utility
  class World with a static method message that capitalizes the first letter of each sentence. Replace the
  System.out.printlns by the World.message (except in the World class, of course!)
- [ ] Of course, a player may be called something like "Carl", and an orc will be "the orc". Lets do two things: let
  make
  the constructor of Player and Creature throw an exception if they don't start with an uppercase and lowercase,
  respectively. Second, let's change the getName() function to return "the " + name in the case of a creature. Also,
  I'm only noticing now that Character is also a Java class-better rename it to Being?

All right: you have some inheritance now, an abstract class, and a utility class. And you should have about 100 lines
of code now. Of course, the inheritance seems yet trivial -why not set a boolean isMonster to decide whether to
put 'the' in front of the name? However, inheritance (and the game) becomes much more interesting once classes
don't simply have different data, but also different behaviors...

- [ ] Enemies may have abilities. For example, if the orc's health drops to 30% or lower, he will enrage, doubling his
  damage. So that you do not put things in Creature class that will not other creatures, make a new class Orc, and two
  other classes (Kobold and Zombie - for later purposes). Anyway, define the Orc, Kobold and Zombie classes, copy the
  hit-method to the Orc class, giving it the annotation @Override to indicate that it overrides the original, and adjust
  the code to work (for example by checking that hitPoints is protected, not private). Orc also needs to get a
  maxHitPoints to check for the enrage condition. Perhaps also override getName to print "the enraged orc hits..."
  And don't forget (like I did) to change the new Creature() to new Orc()...
- [ ] Of course, the output can be nicer; it would be nicer if the orc gives some indication of when it enrages (fix
  that!)
- [ ] As I'm getting more types of enemies now, it may be the time to make a new package (in this case a
  net.ericwubbo.morpg.creature package) to house the creature, orc, kobold and zombie. I do need to make the
  World.message
  method public, but then it runs again.
- [ ] Since I was bitten by the bug of forgetting to rename new Creature to new Orc, I want to avoid such mistakes in
  the
  future. I make the Creature class abstract, so I can't use new Creature(...) anymore, and let the orc constructor
  not require the string "orc" anymore- I just pass that to the Creature constructor.

So this is the first behavior, with some @Override and some abstract. You should have about 150 lines of code now,
but we can still go deeper. Likely (like me), you've copy-pasted methods from the Creature class to Orc; let's now
try make this more maintainable and shorter.

- [ ] Let's first undo some code duplication. The Orc version of hit is almost the same as the Being-version, the
  only difference is the damage. Let's create a getDamage() method in Being and let Orc override it. For getWounded it's
  harder to extract duplicate logic, we can make an updateStatus() method to deduplicate the "dies" output...
- [ ] Encapsulation is one advantage of object-oriented programming. Why would one need to micromanage (in Program.java)
  that an orc has an axe? Can't the Orc class itself decide that? We will now change the constructors for Orc, Zombie
  and
  Kobold to set their names automatically to the correct values, set hitpoints to 20, 30 and 15, and make their weapons
  an
  axe (max damage 3), hands (max damage 2), and mining pick (max damage 4).
- [ ] I change the line Being orc = new Orc(); into Creature monster = new Orc(); renaming the orc, and then try swap
  the orc for the kobold and the zombie. Or better: use Random.next(3) to choose an opponent!
- [ ] Of course, Carl now almost always wins. The game gets boring. Let's make it a bit more exciting! We can now make
  a group of enemies (1-4). For now: all of the same type. Make a method in Program to generate a group of 1-4 of the
  same random enemy type (like 3 zombies). Perhaps make it an ArrayList (array can also work). But I am quickly seeing
  that this can quickly become a mess (which enemy should Carl hit?) I'd better make an EnemyGroup class... Doing stuff
  with such a class and constructors is also a pain, but I can work with EnemyGroup enemyGroup =
  new EnemyGroup(3, Orc::new); and public class EnemyGroup {
  public EnemyGroup(int numCreatures, Supplier<Creature> creatureMaker)
- [ ] Anyway, while the programming is a bit tricky in spots, I am seeing that my survivor code must change: of
  course an EnemyGroup is not a creature, but it still has a name! Perhaps I should put getName in an interface
  (Nameable), and let both the EnemyGroup and Being implement it.

All right.. now I have about 250 lines of code, an interface, and a bunch of methods. Hopefully IDEA helped you fix
some bugs, as it helped me. By all rights, this project has gone long enough and should have demonstrated some
techniques of object-oriented programming. If you got this far, congratulations! Of course, there are still some
niggles with the program, if you also want to solve (some of) those, please check out the bonus material!
