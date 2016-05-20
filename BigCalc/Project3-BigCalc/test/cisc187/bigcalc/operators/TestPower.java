package cisc187.bigcalc.operators;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.After;
import org.junit.Test;
import java.util.Stack;


/**
 * Unit tests for the Power command
 */
public class TestPower {
  Power command;
  Stack<String> operands;

  @Before
  public void setUp() {
    operands = new Stack<>();
    command = new Power();
  }
  @After
  public void tearDown() {
    command = null;
  }

  @Test 
  public void testIsOperator(){
    assertTrue("Failed to detect exponentiation operator.", command.isOperator("^"));
  }
  @Test 
  public void testIsNotOperator(){
    for (char c: "+-0123456789`~!@#$%*/\\&[   ]".toCharArray()) {
      assertFalse("Incorrectly claimed that " + c + " is an exponentiation operator.", 
                  command.isOperator(Character.toString(c)));
    }
  }

  @Test 
  public void test2times2(){
    operands.push("2");
    operands.push("2");
    command.execute(operands, null);
    assertEquals("Failed to raise 2^2", "4", operands.peek());
  }
  @Test(expected=NumberFormatException.class)
  public void testnullBigInt(){
    operands.push("");
    operands.push("2");
    command.execute(operands, null);
  }
  @Test(expected=IllegalArgumentException.class)
  public void testTooFewOperands(){
    operands.push("2");
    operands.push("2");
    operands.pop();
    command.execute(operands, null);
  }
  @Test(expected=NumberFormatException.class)
  public void testExponentNotAnInteger(){
    operands.push("9876543210");
    operands.push("234567890123456789");
    command.execute(operands, null);
  }
  @Test 
  public void test0power1(){
    operands.push("0");
    operands.push("1");
    command.execute(operands, null);
    assertEquals("Failed to raise 0^1", "0", operands.peek());
  }
  @Test 
  public void test1power0(){
    operands.push("1");
    operands.push("0");
    command.execute(operands, null);
    assertEquals("Failed to raise 1^0", "1", operands.peek());
  }
  @Test 
  public void test1power2(){
    operands.push("1");
    operands.push("2");
    command.execute(operands, null);
    assertEquals("Failed to raise 1^2", "1", operands.peek());
  }

  @Test 
  public void test1234567890123456789power1(){
    operands.push("12345678901234567891234567890123456789");
    operands.push("1");
    command.execute(operands, null);
    assertEquals("Failed to raise 12345678901234567891234567890123456789 ^ 1", "12345678901234567891234567890123456789", operands.peek());
  }

  @Test
  public void test4power5(){
    operands.push("4");
    operands.push("5");
    command.execute(operands, null);
    assertEquals("Failed to raise 4^5", "1024", operands.peek());
  }

  @Test 
  public void test9999power99(){
    operands.push("9999");
    operands.push("99");
    command.execute(operands, null);
    assertEquals("Failed to raise 9999 ^ 99", "990148353526723487602263124753282625570559528895791057324326529121794837894053513464422176826916433932586924386677766244032001623756821400432975051208820204980098735552703841362304669970510691243800218202840374329378800694920309791954185117798434329591212159106298699938669908067573374724331208942425544893910910073205049031656789220889560732962926226305865706593594917896276756396848514900989999", operands.peek());
  }
    /*
  @Test 
  public void test2power9999(){
    operands.push("2");
    operands.push("9999");
    command.execute(operands, null);
    assertEquals("Failed to raise 2 ^ 9999", "9975315584403791924418710813417925419117484159430962274260044749264719415110973315959980842018097298949665564711604562135778245674706890558796892966048161978927865023396897263382623275633029947760275043459096655771254304230309052342754537433044812444045244947419004626970816628925310784154736951278456194032612548321937220523379935813492726611434269080847157887814820381418440380366114267545820738091978190729484731949705420480268133910532310713666697018262782824765301571340117484700167967158325729648886639832887803086291015703997099089803689122841881140018651442743625950417232290727325278964800707416960807867294069628547689884559638900413478867837222061531009378918162751364161894635355186901433196515714066620700812097835845287030709827171162319400624428073652603715996129805898125065496430120854170403802966160080634246144248127920656422030768369475743557128157555544872757101656910101465820478798232378005202922920783036022481433508257530960315502093211137954335450287303208928475955728027534125625203003759921130949029618559027222394036453197621274169610991353702236581188380423306516889353019901706598566746827311350281584968727754120890486405491645657201785938762384254928638468963216610799699938443330404184418919013821641387586136828786372392056147194866905430803711626645987406560098802089140982848737949082265629217067979931392065064092703141738324544345260523790441307911980992885061203522165291537934519659802301702486578291604336052956650451876411707769872697198857628727645255106155473660805376737412870387636993174149249170378468977823319310937284749639508286051850682216567908607155895699111491922923667220135482091425502536463874182275289317250550426493906194736964349770417173079403521979559492907572889588571809849364065729741891601040737491085929005694535614125452913408718110288737960708826857843862807452291452496230514315040767791654065050993837928117171769477704587811700422443763081321784324416759731860188646620047228123461627175200339013636918877688203363449318120518745705483359278525379549050123394940089135962976690641210977014151379704224477507338334194848998443120818156688196951686727900703818370938855527692112869749555093234109848290825742565247111184973857381534577734108841438100181388628861890682665805598405640396334740943600649321830384275819930267301148935778758973692623184723461543947132974108504025560161182748144084517869560684169196795878209366925255485135806957719795495799077327208668155828468015561124968984999613390866179011555931322287649567879087504099919618142307624940544480116122181086885809043178507734242029311164896426937811743278220268481311009481785514406180783756271669151635014548834325284278578752758363759449597064855668845074958090657585772003864325286594778725460165092652423556909157703662026659519231042018210881851955775319894500371426836098140451738987266660234184397934290118976109314560040371409775658974078812224149259230754852444013637360787344065797375204866057540249095227901708413474893570658031605343195755840887152396298354688", operands.peek());
  }
  */

  /*
  @Test
  //Takes about 2min 5s to pass
  public void test2power4000(){
    operands.push("2");
    operands.push("4000");
    command.execute(operands, null);
    assertEquals("Failed to raise 2 ^ 4000", "13182040934309431001038897942365913631840191610932727690928034502417569281128344551079752123172122033140940756480716823038446817694240581281731062452512184038544674444386888956328970642771993930036586552924249514488832183389415832375620009284922608946111038578754077913265440918583125586050431647284603636490823850007826811672468900210689104488089485347192152708820119765006125944858397761874669301278745233504796586994514054435217053803732703240283400815926169348364799472716094576894007243168662568886603065832486830606125017643356469732407252874567217733694824236675323341755681839221954693820456072020253884371226826844858636194212875139566587445390068014747975813971748114770439248826688667129237954128555841874460665729630492658600179338272579110020881228767361200603478973120168893997574353727653998969223092798255701666067972698906236921628764772837915526086464389161570534616956703744840502975279094087587298968423516531626090898389351449020056851221079048966718878943309232071978575639877208621237040940126912767610658141079378758043403611425454744180577150855204937163460902512732551260539639221457005977247266676344018155647509515396711351487546062479444592779055555421362722504575706910949376", operands.peek());
  }
  */


}
