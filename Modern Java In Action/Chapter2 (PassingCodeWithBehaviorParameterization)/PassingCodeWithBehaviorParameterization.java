
public class Chapter2PassingCodeWithBehaviorParameterization {}

/*
	Yazılım mühendisliğinde iyi bilinen bir sorun, ne yaparsanız yapın, kullanıcı gereksinimlerinin değişeceğidir. 
	Örneğin, bir çiftçinin envanterini anlamasına yardımcı olacak bir uygulama düşünün. 
	Çiftçi, envanterindeki tüm yeşil elmaları bulmak için bir işlevsellik isteyebilir. 
	Ama ertesi gün size, \"Aslında, tüm elmaları 150 g'dan daha ağır bulmak istiyorum\" diyebilir. 
	İki gün sonra çiftçi geliyor\nGeri dönüp ekliyor: \"Yeşil ve 150 g'dan daha ağır olan tüm elmaları bulabilseydim gerçekten güzel olurdu.
	\" Bu değişen gereksinimlerle nasıl başa çıkabilirsiniz? İdeal olarak, mühendislik çabalarınızı en aza indirmek istersiniz. 
	Ek olarak, benzer yeni işlevlerin uygulanması kolay olmalı ve uzun vadede sürdürülebilmelidir.
	
	Davranış parametrelendirmesi, sık gereksinim değişikliklerini işlemenizi sağlayan bir yazılım geliştirme modelidir. 
	Özetle, bir kod bloğu almak ve yürütmeden kullanılabilir hale getirmek anlamına gelir.
	
	Bu kod bloğu daha sonra programlarınızın diğer bölümleri tarafından çağrılabilir, bu da söz konusu kod bloğunun yürütülmesini erteleyebileceğiniz anlamına gelir. 
	Örneğin, kod bloğunu bağımsız değişken olarak daha sonra yürütecek başka bir yönteme geçirebilirsiniz. 
	Sonuç olarak, yöntemin davranışı bu kod bloğuna göre parametrelendirilir. 
	Örneğin, bir koleksiyonu işlerseniz, aşağıdaki yöntemi yazmak isteyebilirsiniz:
	
		Bir listenin her öğesi için \"bir şey\" yapabilir, bir yöntem yazmak isteyebilirsiniz
		Listeyi işlemeyi bitirdiğinizde \"başka bir şey\" yapabilir
		Bir hatayla karşılaşırsanız \" başka bir şey\" yapabilir
	
	
	Davranış parametrelendirmesinin ifade ettiği şey budur. 
	İşte bir benzetme: oda arkadaşınız süpermarkete nasıl gideceğini ve eve nasıl döneceğini biliyor. 
	Ona ekmek, peynir ve şarap gibi şeylerin bir listesini almasını söyleyebilirsiniz. 
	Bu, goAndBuy yönteminin bağımsız değişken olarak bir ürün listesini geçirmesine eşdeğerdir. 
	
	Ama bir gün ofistesiniz ve postaneden bir paket almadan önce hiç yapmadığı bir şey yapması için ona ihtiyacınız var. 
	Ona talimatların bir listesini geçirmeniz gerekir: Postaneye git, bu referans numarasını kullanın, yöneticiyle konuşun ve parseli alın. 
	Ona talimatların listesini e -posta ile aktarabilirsiniz ve aldığında talimatları takip edebilir. 
	Şimdi, Goanddo yöntemine eşdeğer olan ve argüman olarak çeşitli yeni davranışları yürütebilen biraz daha gelişmiş bir şey yaptınız.
	
	Bu bölüme, değişim gereksinimleri için daha esnek olmak için kodunuzu nasıl geliştirebileceğinize bir örnek olarak size yol açarak başlayacağız.
	Bu bilgiye dayanarak, birkaç gerçek dünya örneği için davranış parametrelemesinin nasıl kullanılacağını gösteriyoruz.
	
	Örneğin, bir Listeyi sıralamak, dosyaların adlarını filtrelemek veya bir İş Parçacığına bir kod bloğu yürütmesini ve hatta GUI olay işlemesini söylemek için Java API'deki mevcut sınıfları ve arabirimleri kullanarak davranış parametrelendirme desenini zaten kullanmış olabilirsiniz.
	
	Yakında bu kalıbın Java'da tarihsel olarak ayrıntılı olduğunu fark edeceksiniz. 
	Java 8'den itibaren Lambda ifadeleri ayrıntı sorununu çözer. 
	3. bölümde lambda ifadelerinin nasıl oluşturulacağını, nerede kullanılacağını ve bunları benimseyerek kodunuzu nasıl daha özlü hale getirebileceğinizi göstereceğiz. 
	
*/
	

/*
 			Coping with chancing requirements

 	Değişen gereksinimlerle başa çıkabilecek kod yazmak zordur. 
 	Kodunuzu daha esnek hale getirmek için bazı en iyi uygulamaları göstererek kademeli olarak geliştireceğimiz bir örneği inceleyelim. 
 	Bir çiftlik envanteri uygulaması bağlamında, yeşil elmaları bir listeden filtrelemek için bir işlevsellik uygulamanız gerekir. 
 	Kulağa kolay geliyor, değil mi?
 	
*/



/*
   				First attempt: filtering green apples

	Bölüm 1'de olduğu gibi, bir elmanın farklı renklerini temsil etmek için mevcut bir Renk enumunuz olduğunu varsayalım:
	enum Color{RED,GREEN}
	
	A first solution might be as follows:
		public static List<Apple> filterGreenApples(List<Apple> inventory) {
			List<Apple> result = new ArrayList();
			for(Apple apple : inventory)
				if(GREEN.equals(apple.getColor()) ****
					result.add(apple);
				}
			}
			return result
		}
		
		Vurgulanan yıldız, yeşil elmaları seçmek için gereken koşulu gösterir.
		YEŞİL gibi bir renk kümesine sahip bir Renk enumunuz olduğunu varsayabilirsiniz. 
		Ama şimdi çiftçi fikrini değiştiriyor ve kırmızı elmaları da filtrelemek istiyor. 
		Ne yapabilirsiniz?
		
		Naif bir çözüm, yönteminizi çoğaltmak, filterRedApples olarak yeniden adlandırmak ve if koşulunu kırmızı elmalarla eşleşecek şekilde değiştirmek olacaktır.
		Bununla birlikte, bu yaklaşım, çiftçi birden fazla renk istiyorsa değişikliklerle iyi başa çıkmaz. 
		İyi bir ilke şudur: Kendinizi neredeyse tekrarlanan kod yazarken bulduğunuzda, bunun yerine soyutlamaya çalışın.

*/



/*
  				Second attempt: parameterizing the color
  	
  	Filtre RedApples yapmak için filterGreenApples'daki kodun çoğunu çoğaltmaktan nasıl kaçınırız? 
  	Rengi parametreleştirmek ve bu tür değişikliklere karşı daha esnek olmak için yapabileceğiniz şey, yönteminize bir parametre eklemektir:			

	public static List<Apple> filterApplesByColor(List<Apple> inventory, Color color)
	{
		List<Apple> result = new ArrayList<>();
		for (Apple apple: inventory) {
			if(apple.getColor().equals(color))
				result.add(apple);
		}
		return result;
	}
	
	You can now make the farmer happy and invoke your method as follows:
	
	List<Apple> greenApples = filterApplesByColor(inventory, GREEN);
	List<Apple> redApples = filterApplesByColor(inventory, RED);
	
	Çok kolay, değil mi? Örneği biraz karmaşıklaştıralım. 
	Çiftçi size geri döner ve şöyle der: \"Hafif elmalar ve ağır elmalar arasında ayrım yapmak gerçekten harika olurdu. Ağır elmalar tipik olarak 150 g'dan daha büyük bir ağırlığa sahiptir. \"
	
	Yazılım mühendisliği şapkanızı takarak, çiftçinin ağırlığı değiştirmek isteyebileceğini önceden fark edersiniz. 
	Bu nedenle, ek bir parametre aracılığıyla çeşitli ağırlıklarla başa çıkmak için aşağıdaki yöntemi oluşturursunuz:
	
	public static List<Apple> filterApplesByWeight(List<Apple> inventory, 
	int weight) {
 		List<Apple> result = new ArrayList<>();
 		For (Apple apple: inventory){
 			if ( apple.getWeight() > weight ) {
 				result.add(apple);
 			}
 		}
 		return result;
	}
	
	Bu iyi bir çözümdür, ancak envanteri geçmek ve filtreleme ölçütlerini her elmaya uygulamak için uygulamanın çoğunu nasıl çoğaltmanız gerektiğine dikkat edin. 
	Bu biraz hayal kırıklığı yaratıyor çünkü yazılım mühendisliğinin DRY (kendinizi tekrar etmeyin) ilkesini kırıyor. 
	
	Performansı artırmak için filtre geçişini değiştirmek isterseniz ne olur? 
	Artık yalnızca tek bir yöntem yerine tüm yöntemlerinizin uygulanmasını değiştirmeniz gerekir. 
	Bu, mühendislik çabası perspektifinden pahalıdır.
	
	Renk ve ağırlığı filtre adı verilen tek bir yöntemde birleştirebilirsiniz. 
	Ancak o zaman yine de hangi özelliğe filtre uygulamak istediğinizi ayırt etmenin bir yoluna ihtiyacınız olacaktır.
	Renk ve ağırlık sorgularını ayırt etmek için bir bayrak ekleyebilirsiniz.
*/ 




/*
				Third attempt: filtering with every attribute you can think of
				
	Tüm öznitelikleri birleştirmeye yönelik çirkin bir girişim aşağıdaki gibi olabilir:
	
	public static List<Apple> filterApples(List<Apple> inventory, Color color,
 												int weight, boolean flag) {
 		List<Apple> result = new ArrayList<>();
 		for (Apple apple: inventory) {
 			if ( (flag && apple.getColor().equals(color)) || 
 				(!flag && apple.getWeight() > weight) ){ 
 				result.add(apple);
 			}
 		}
 		return result;
	}

	
	You could use this as follows (but it’s ugly):
	
	List<Apple> greenApples = filterApples(inventory, GREEN, 0, true);
	List<Apple> heavyApples = filterApples(inventory, null, 150, false);
	
	
	Bu çözüm son derece kötü. İlk olarak, istemci kodu korkunç görünüyor. 
	Doğru ve yanlış ne anlama geliyor? 
	Ek olarak, bu çözüm değişen gereksinimlerle iyi başa çıkmaz.
	
	Çiftçi sizden bir elmanın farklı özellikleriyle, örneğin büyüklüğü, şekli, kökeni vb. İle filtrelemenizi isterse ne olur? 
	Ayrıca, çiftçi sizden yeşil elmalar gibi özellikleri birleştiren daha karmaşık sorgular isterse ne olur?\nağır? Birden çok yinelenen filtre yönteminiz veya son derece karmaşık bir yönteminiz olur. 
	Şimdiye kadar, filterApples yöntemini String, Integer, enum türü veya boolean gibi değerlerle parametrelendirdiniz. 
	Bu, bazı iyi tanımlanmış problemler için iyi olabilir. 
	Ancak bu durumda, ihtiyacınız olan şey, filtre-Elma yönteminize elmalar için seçim kriterlerini söylemenin daha iyi bir yoludur. 
	Bir sonraki bölümde, bu esnekliği elde etmek için davranış parametrelendirmesinin nasıl kullanılacağını açıklayacağız.
*/





/*
 			Behavior parameterization
 	
 	Önceki bölümde, değişen gereksinimlerle başa çıkmak için çok sayıda parametre eklemekten daha iyi bir yola ihtiyacınız olduğunu gördünüz. 
 	Geri adım atalım ve daha iyi bir soyutlama seviyesi bulalım.
 	Olası bir çözüm, seçim kriterlerinizi modellemektir: elmalarla çalışıyorsunuz ve Apple'ın bazı özelliklerine dayanarak bir boolean döndürüyorsunuz. 
 	Örneğin, yeşil mi? 150 g'dan daha mı ağır? 
 	Buna yüklem diyoruz (boolean döndüren bir işlev). 
 	Bu nedenle, seçim kriterlerini modellemek için bir arayüz tanımlayalım:
 	
 	public interface ApplePredicate
 	{
 		boolean test(Apple apple);
 	}
 	
 	
 	Artık aşağıda gösterildiği gibi (ve Şekil 2.1'de gösterilmiştir) farklı seçim kriterlerini temsil etmek için ApplePrediate'in birden fazla uygulamasını bildirebilirsiniz:
 	
 	public class AppleHeavyWeightPredicate implements ApplePredicate {
 		public boolean test(Apple apple) {
 			return apple.getWeight() > 150; 
 		}
 	}
 	
 	public class AppleGreenColorPredicate implements ApplePredicate 
 	{ 
 		public boolean test(Apple apple) {
 			return GREEN.equals(apple.getColor());
 		}
	}
 	

 	Bu kriterleri filtre yöntemi için farklı davranışlar olarak görebilirsiniz. 
 	Az önce yaptığınız şey, bir algoritma ailesini tanımlamanıza, her algoritmayı (strateji denir) kapsüllemenize olanak tanıyan ve bir algoritma seçmenize olanak tanıyan strateji tasarım modeli (bkz. işlem esnasında. 
 	Bu durumda Algo Rithms ailesi ApplePrediate'dir ve farklı stratejiler AppleHeavyweightPrediate ve AppleGreenColorpredicate'dir.
 	
 	Ancak ApplePredicate'in farklı uygulamalarından nasıl yararlanabilirsiniz? 
 	Apple'da bir koşulu test etmek üzere ApplePredicate nesnelerini kabul etmek için filterApples yönteminize ihtiyacınız vardır. 
 	Davranış parametrelendirmesinin anlamı budur: bir yönteme birden fazla davranışı (veya stratejiyi) parametre olarak alma ve bunları farklı davranışları gerçekleştirmek için dahili olarak kullanma yeteneği.
 	
 	
*/


