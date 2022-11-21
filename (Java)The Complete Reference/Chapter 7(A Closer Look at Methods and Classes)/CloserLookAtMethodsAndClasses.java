package chapter7closerlookmethodsandclasses;

import java.security.cert.CertPathValidatorResult;

public class CloserLookMethodsAndClasses {}


/*--------------------------------------------------------------------------------------------------------------------------------------------------------------
 		Overloading Methods
 		
	Java'da, parametre bildirimleri farklı olduğu sürece, aynı sınıf içinde aynı adı paylaşan iki veya daha fazla yöntem tanımlamak mümkündür. 
	Bu durumda, yöntemlerin aşırı yüklendiği söylenir ve işleme yöntem aşırı yüklemesi denir. 
	Yöntem aşırı yükleme, Java'nın polimorfizmi desteklemesinin yollarından biridir. 
	Yöntemlerin aşırı yüklenmesine izin veren bir dili hiç kullanmadıysanız, biraz garip görünebilir. 
	Ancak göreceğiniz gibi, yöntem aşırı yükleme, Java'nın en heyecan verici ve kullanışlı özelliklerinden biridir.
	
	Aşırı yüklenmiş bir yöntem çağrıldığında, Java aşırı yüklenmiş yöntemin hangi sürümünün gerçekten çağrılacağını belirlemek için kılavuz olarak bağımsız değişkenlerin türünü veya sayısını kullanır. 
	Bu nedenle, aşırı yüklenmiş yöntemler, parametrelerinin türü veya sayısı bakımından farklılık göstermelidir. 
	Aşırı yüklenmiş yöntemlerin farklı dönüş türleri olsa da, dönüş türü tek başına bir yöntemin iki sürümünü ayırt etmek için yetersizdir. 
	Java aşırı yüklenmiş bir yönteme yapılan bir çağrıyla karşılaştığında, parametreleri çağrıda kullanılan bağımsız değişkenlerle eşleşen yöntemin sürümünü yürütür. 
	Aşağıda, yöntemin aşırı yüklenmesini gösteren basit bir örnek verilmiştir
	
--------------------------------------------------------------------------------------------------------------------------------------------------------------*/

//class OverloadDemo 
//{
//	void test()
//	{
//		System.out.println("No parameters");
//	}
//	
//
//	void test(int a)
//	{
//		System.out.println("a: "+a);
//	}
//	
//	void test(int a, int b)
//	{
//		System.out.println("a and b" + a +" "+ b);
//	}
//	
//	double test(double a)
//	{
//		System.out.println("double a: " + a);
//		return a*a;
//	}
//
//}
//
//
//class Overload
//{
//	public static void main(String[] args) 
//	{
//		OverloadDemo ob = new OverloadDemo();
//		double result;
//		
//		ob.test();
//		ob.test(10);
//		ob.test(10,20);
//		result = ob.test(123.25);
//		
//		System.out.println("Result of ob.test(123.25)" + result);
//	}
//}

/*--------------------------------------------------------------------------------------------------------------------------------------------------------------
 	Gördüğünüz gibi, test( ) dört kez aşırı yükleniyor. 
 	İlk sürüm parametre almaz, ikincisi bir tamsayı parametresi alır, üçüncüsü iki tamsayı parametresi alır ve dördüncüsü bir çift parametre alır. 
 	test( ) öğesinin dördüncü sürümünün de bir değer döndürmesi, aşırı yüklenmeye göre bir sonuç doğurmaz, çünkü dönüş türleri aşırı yük çözümlemesinde rol oynamaz.


	Aşırı yüklenmiş bir yöntem çağrıldığında, Java yöntemi çağırmak için kullanılan bağımsız değişkenler ile yöntemin parametreleri arasında bir eşleşme arar. 
	Ancak, bu eşleşmenin her zaman kesin olması gerekmez. 
	Bazı durumlarda, Java'nın otomatik tür dönüştürmeleri aşırı yük çözümlemesinde rol oynayabilir. 
	Örneğin, aşağıdaki programı göz önünde bulundurun:

--------------------------------------------------------------------------------------------------------------------------------------------------------------*/

//class OverloadDemo
//{
//	void test()
//	{
//		System.out.println("No parameters");
//	}
//	
//	void test(int a,int b)
//	{
//		System.out.println("a and b:" + a + " "+b);
//	}
//	
//	void test(double a)
//	{
//		System.out.println("Inside test(double) a: "+ a);
//	}
//}
//
//class Overload
//{
//	public static void main(String[] args) 
//	{
//		OverloadDemo ob = new OverloadDemo();
//		int i = 88;
//		
//		ob.test();
//		ob.test(10, 20);
//		
//		ob.test(i);
//		ob.test(123.2);
//		
//	}
//}

/*--------------------------------------------------------------------------------------------------------------------------------------------------------------
 	This program generates the following output:
 	No parameters
 	a and b: 10 20
 	Inside test(double) a: 88.0
 	Inside test(double) a: 123.2
--------------------------------------------------------------------------------------------------------------------------------------------------------------*/

/*--------------------------------------------------------------------------------------------------------------------------------------------------------------
	Gördüğünüz gibi, OverloadDemo'nun bu sürümü test(int)'i tanımlamıyor. 
	Bu nedenle, test( ) Overload içinde bir tamsayı bağımsız değişkeniyle çağrıldığında, eşleşen bir yöntem bulunmaz. 
	Ancak, Java bir tamsayıyı otomatik olarak double dönüştürebilir ve bu dönüştürme çağrıyı çözmek için kullanılabilir. 
	Bu nedenle, test(int) bulunamadıktan sonra, Java i'yi double yükseltir ve ardından test(double)'ı çağırır. 
	Tabii ki, eğer test(int) tanımlanmış olsaydı, bunun yerine çağrılırdı. 
	Java, otomatik tür dönüşümlerini yalnızca tam eşleşme bulunamazsa kullanır.

	Yöntem aşırı yüklemesi polimorfizmi destekler, çünkü Java'nın \"tek arayüz, çoklu yöntem\" paradigmasını uygulamasının bir yoludur.
	Nasıl yapılacağını anlamak için aşağıdakileri göz önünde bulundurun.
	Yöntemin aşırı yüklenmesini desteklemeyen dillerde, her yönteme benzersiz bir ad verilmelidir.
	Bununla birlikte, sıklıkla farklı veri türleri için temelde aynı yöntemi uygulamak isteyeceksiniz. 
	overload özelliği olmayan dilleri düşünelim ve bu dillerde bi mutlak değer işlemi gerçekleştirelim.
	Örneğin, C'de abs( ) fonksiyonu bir tamsayının mutlak değerini, labs( ) fonksiyonu long bir tamsayının mutlak değerini ve fabs( ) fonksiyonu float değerinin mutlak değerini döndürür.
	C aşırı yüklemeyi desteklemediğinden, her üç işlev de temelde aynı şeyi yapsa bile, her işlevin kendi adı vardır.
	Bu, durumu kavramsal olarak gerçekte olduğundan daha karmaşık hale getirir. 
	Her işlevin altında yatan kavram aynı olsa da, yine de hatırlanması gereken üç adınız vardır. 
	Bu durum Java'da oluşmaz, çünkü her mutlak değer yöntemi aynı adı kullanabilir.
	java'da Math.abs( ) adı verilen mutlak bir değer yöntemi içerir. 
	Bu yöntem, tüm sayısal türleri işlemek için Java'nın Math sınıfı tarafından aşırı yüklenir. 
	Java, bağımsız değişken türüne bağlı olarak hangi abs( ) sürümünün çağrılacağını belirler.
	
	Aşırı yüklemenin değeri, ilgili yöntemlere ortak bir ad kullanılarak erişilmesine izin vermesidir. Bu nedenle, abs adı gerçekleştirilen genel eylemi temsil eder.
	Belirli bir durum için doğru belirli sürümü seçmek derleyiciye bırakılır. 
	Siz, programcı, sadece gerçekleştirilen genel işlemi hatırlamanız gerekir. 
	Polimorfizmin uygulanmasıyla, birkaç isim bire indirgenmiştir. 
	Bu örnek oldukça basit olsa da, kavramı genişletirseniz, aşırı yüklemenin daha fazla karmaşıklığı yönetmenize nasıl yardımcı olabileceğini görebilirsiniz.
	
	Bir yöntemi aşırı yüklediğinizde, bu yöntemin her sürümü istediğiniz herhangi bir etkinliği gerçekleştirebilir. 
	Aşırı yüklenmiş yöntemlerin birbiriyle ilişkili olması gerektiğini belirten bir kural yoktur. Bununla birlikte, stilistik bir bakış açısından, aşırı yükleme yöntemi bir ilişki anlamına gelir. 
	Bu nedenle, ilgisiz yöntemleri aşırı yüklemek için aynı adı kullanabilirsiniz, ancak kullanmamalısınız. 
	Örneğin, bir tamsayının karesini ve kayan nokta değerinin karekökünü döndüren yöntemler oluşturmak için sqr adını kullanabilirsiniz. 
	Ancak bu iki operasyon temelde farklıdır. 
	Aşırı yükleme yöntemini bu şekilde uygulamak, asıl amacını yener. 
	Uygulamada, yalnızca yakından ilişkili işlemleri aşırı yüklemelisiniz.
	
--------------------------------------------------------------------------------------------------------------------------------------------------------------*/



/*--------------------------------------------------------------------------------------------------------------------------------------------------------------
 		Overloading Constructors

 	Normal yöntemlere aşırı yüklenmenin yanı sıra, oluşturucu yöntemlerini de aşırı yükleyebilirsiniz. 
 	Aslında, oluşturduğunuz çoğu gerçek dünya sınıfı için, aşırı yüklenmiş oluşturucular istisna değil, norm olacaktır. 
 	Nedenini anlamak için, önceki bölümde geliştirilen Box sınıfına geri dönelim. 		
--------------------------------------------------------------------------------------------------------------------------------------------------------------*/

//class Box
//{
//	double width;
//	double height;
//	double depth;
//	
//	Box(double w, double h, double d)
//	{
//		width = w;
//		height = h;
//		depth = d;
//	}
//	
//	double volume()
//	{
//		return width * height * depth;
//	}
//}

/*--------------------------------------------------------------------------------------------------------------------------------------------------------------
 	Gördüğünüz gibi, Box( ) oluşturucu üç parametre gerektirir. 
 	Bu, Box nesnelerinin tüm bildirimlerinin Box( ) yapıcısına üç bağımsız değişken geçirmesi gerektiği anlamına gelir. 
 	Örneğin, aşağıdaki deyim şu anda geçersizdir:
 	
 	Box ob = new Box();
 	
 	Box( ) üç bağımsız değişken gerektirdiğinden, onlarsız çağırmak bir hatadır. 
 	Bu bazı önemli soruları gündeme getiriyor. 
 	Ya sadece bir kutu istediyseniz ve ilk boyutlarının ne olduğunu umursamadıysanız (veya bilmiyorsanız)? 
 	Veya, üç boyutun tümü için kullanılacak yalnızca bir değer belirterek bir küpü başlatabilmek istiyorsanız ne olur? 
 	Box sınıfı şu anda yazıldığından, bu diğer seçenekleri kullanamazsınız.
 	
 	Neyse ki, bu sorunların çözümü oldukça kolaydır: Box constructor'ı aşırı yükleyin, böylece az önce açıklanan durumları ele alır. 
 	İşte Box'ın geliştirilmiş bir sürümünü içeren ve tam da bunu yapan bir program:
 	
--------------------------------------------------------------------------------------------------------------------------------------------------------------*/

//class Box 
//{
//	double width;
//	double height;
//	double depth;
//	
//	Box(double w,double h,double d)
//	{
//		width = w;
//		height = h;
//		depth = d;
//	}
//	
//	Box()
//	{
//		width = -1;
//		height = -1;
//		depth = -1;
//	}
//	
//	Box(double len)
//	{
//		width = height = depth = len;
//	}
//	
//	double volume()
//	{
//		return width * height * depth;
//	}
//}
//
//
//class OverloadCons
//{
//	public static void main(String[] args) 
//	{
//		Box mybox1 = new Box(10,20,15);
//		Box mybox2 = new Box();
//		Box mycube = new Box(7);
//		
//
//	}
//}

/*--------------------------------------------------------------------------------------------------------------------------------------------------------------
	Gördüğünüz gibi, uygun aşırı yüklenmiş oluşturucu, yeni yürütülürken belirtilen bağımsız değişkenlere göre çağrılır.
--------------------------------------------------------------------------------------------------------------------------------------------------------------*/




/*--------------------------------------------------------------------------------------------------------------------------------------------------------------
 			Using Objects as Parameters
 			
 	Şimdiye kadar, yöntemlere parametre olarak sadece basit türleri kullanıyoruz. Ancak, nesneleri yöntemlere geçirmek hem doğru hem de yaygındır. 
 	Örneğin, aşağıdaki kısa programı göz önünde bulundurun:		
--------------------------------------------------------------------------------------------------------------------------------------------------------------*/


//class Test
//{
//	int a,b;
//	
//	Test(int i, int j)
//	{
//		a = i;
//		b = j;
//	}
//	
//	boolean equalTo(Test o)
//	{
//		if(o.a == a && o.b == b) return true;
//		else return false;
//	}
//
//}
//
//class PassOb
//{
//	public static void main(String[] args) 
//	{
//		Test ob1 = new Test(100,22);
//		Test ob2  =new Test(100,22);
//		Test ob3 = new Test(-1,-1);
//		
//		System.out.println("ob1 == ob2: " + ob1.equalTo(ob2));
//		System.out.println("ob1 == ob3: " + ob1.equalTo(ob3));
//		
//	}
//	//This program generates the following output:
//	//	 ob1 == ob2: true
//	//	 ob1 == ob3: false
//}

/*--------------------------------------------------------------------------------------------------------------------------------------------------------------
	Gördüğünüz gibi, Test içindeki equalTo( ) yöntemi eşitlik için iki nesneyi karşılaştırır ve sonucu döndürür. 
	Diğer bir deyişle, çağıran nesneyi geçirilen nesneyle karşılaştırır. 
	Aynı değerleri içeriyorsa, yöntem true değerini döndürür. 
	Aksi takdirde, false değerini döndürür. 
	equalTo( ) içindeki o parametresinin türü olarak Test'i belirttiğine dikkat edin. 
	Test, program tarafından oluşturulan bir sınıf türü olmasına rağmen, Java'nın yerleşik türleriyle aynı şekilde kullanılır.

	Nesne parametrelerinin en yaygın kullanımlarından biri oluşturucuları içerir. 
	Sık sık, başlangıçta varolan bazı nesnelerle aynı olması için yeni bir nesne oluşturmak isteyeceksiniz. 
	Bunu yapmak için, sınıfının bir nesnesini parametre olarak alan bir oluşturucu tanımlamanız gerekir. 
	Örneğin, Box'ın aşağıdaki sürümü bir nesnenin diğerini başlatmasına izin verir:

--------------------------------------------------------------------------------------------------------------------------------------------------------------*/

//class Box
//{
//	double width;
//	double height;
//	double depth;
//	
//	
//	Box(Box ob)
//	{
//		width = ob.width;
//		height = ob.height;
//		depth = ob.depth;
//	}
//	
//	Box(double w, double h, double d)
//	{
//		width = w;
//		height = h;
//		depth = d;
//	}
//	
//	Box()
//	{
//		width = -1;
//		height = -1;
//		depth = -1;
//	}
//	
//	Box(double len)
//	{
//		width = height = depth = len;
//	}
//	
//	double volume()
//	{
//		return width * height * depth;
//	}
//}
//
//
//class OverloadCons2
//{
//	
//	public static void main(String[] args) 
//	{
//		Box mybox1 = new Box(10, 20, 15);
//		Box mybox2 = new Box();
//		Box mycube = new Box(7);
//		
//		Box myclone = new Box(mybox1);
//		
//		double vol;
//		
//		vol = mybox1.volume();
//		
//		System.out.println("Volume of mybox1 is " + vol);
//		 // get volume of second box
//		vol = mybox2.volume();
//		System.out.println("Volume of mybox2 is " + vol);
//		 // get volume of cube
//		vol = mycube.volume();
//		System.out.println("Volume of cube is " + vol);
//		 // get volume of clone
//		vol = myclone.volume();
//		System.out.println("Volume of clone is " + vol);
//	}
//	
//}

/*--------------------------------------------------------------------------------------------------------------------------------------------------------------
 	Kendi sınıflarınızı oluşturmaya başladığınızda göreceğiniz gibi, nesnelerin uygun ve verimli bir şekilde inşa edilmesine izin vermek için genellikle birçok oluşturucu biçimi sağlamak gerekir.

--------------------------------------------------------------------------------------------------------------------------------------------------------------*/



/*--------------------------------------------------------------------------------------------------------------------------------------------------------------
 			A Closer Look at Argument Passing
	
	Genel olarak, bir bilgisayar dilinin bir argümanı bir alt yordama geçirmesinin iki yolu vardır.
	ilk yol call-by-value
	Bu yaklaşım, bağımsız değişkenin değerini alt yordamın biçimsel parametresine kopyalar.
	Bu nedenle, alt yordamın parametresinde yapılan değişikliklerin bağımsız değişken üzerinde hiçbir etkisi yoktur.
	
	Bir argümanın aktarılabilmesinin ikinci yolu call-by-reference
	Bu yaklaşımda, parametreye bir bağımsız değişkene başvuru(referans) (bağımsız değişkenin değeri değil) geçirilir.
	Alt yordamın içinde, bu başvuru çağrıda belirtilen gerçek bağımsız değişkene erişmek için kullanılır.
	Bu, parametrede yapılan değişikliklerin alt yordamı çağırmak için kullanılan bağımsız değişkeni etkileyeceği anlamına gelir.
	Göreceğiniz gibi, Java tüm bağımsız değişkenleri iletmek için değere göre çağrı kullansa da, kesin etki ilkel bir türün mü yoksa referans türünün mi geçirildiği arasında farklılık gösterir.
	
	İlkel bir türü bir yönteme geçtiğinizde, call-by-value geçilir.
	Böylece, bağımsız değişkenin bir kopyası yapılır ve argümanı alan parametrenin ne olduğu yöntemin dışında bir etkisi yoktur. 
	Örneğin, aşağıdaki programı düşünün:
--------------------------------------------------------------------------------------------------------------------------------------------------------------*/


//class Test
//{
//	void meth(int i, int j)
//	{
//		i *= 2;
//		i /= 2;
//	}
//}
//
//
//class CallByValue
//{
//	public static void main(String[] args) 
//	{
//		Test ob = new Test();
//		
//		int a = 15, b = 20;
//		
//		System.out.println("a and b before call :" + a + " " + b);
//		
//		ob.meth(a, b);
//		
//		System.out.println("a and b after call: "+ a + " " + b);
//		
//		//The output from this program is shown here:
//		// a and b before call: 15 20
//		// a and b after call: 15 20
//	}
//}

/*--------------------------------------------------------------------------------------------------------------------------------------------------------------
 
 	Gördüğünüz gibi, meth( ) içinde gerçekleşen işlemlerin çağrıda kullanılan a ve b değerleri üzerinde hiçbir etkisi yoktur; buradaki değerleri 30 ve 10 olarak değişmedi.
 	Bir nesneyi bir yönteme geçirdiğinizde, durum önemli ölçüde değişir, çünkü nesneler etkin bir şekilde başvuru yoluyla çağrılan şey tarafından geçirilir. 
 	Sınıf türünde bir değişken oluşturduğunuzda, yalnızca bir nesneye başvuru oluşturduğunuzu unutmayın.
  	Bu nedenle, bu başvuruyu bir yönteme geçirdiğinizde, onu alan parametre, bağımsız değişken tarafından başvurulanla aynı nesneye başvurur. 
  	Bu, etkili bir şekilde, nesnelerin call-by-reference kullanılarak yöntemlere geçirilmiş gibi davrandığı anlamına gelir. 
  	Yöntemin içindeki nesnede yapılan değişiklikler, bağımsız değişken olarak kullanılan nesneyi etkiler. 
  	Örneğin, aşağıdaki programı göz önünde bulundurun:
--------------------------------------------------------------------------------------------------------------------------------------------------------------*/

//class Test
//{
//	int a,b;
//	
//	Test(int i, int j)
//	{
//		a = i;
//		b = j;
//	}
//	
//	void meth(Test o)
//	{
//		o.a *= 2;
//		o.b /= 2;
//	}
//}
//
//class PassObjRef
//{
//	public static void main(String[] args) 
//	{
//		Test ob = new Test(15,20);
//		
//		System.out.println("ob.a and ob.b before call: "+ ob.a + " " + ob.b);
//		
//		ob.meth(ob);
//		
//		System.out.println("ob.a and ob.b after call:" + ob.a + " " + ob.b);
//	}
//	
//	//This program generates the following output:
//	//ob.a and ob.b before call: 15 20
//	//ob.a and ob.b after call: 30 10
//	//Gördüğünüz gibi, bu durumda, meth( ) içindeki eylemler bağımsız değişken olarak kullanılan nesneyi etkilemiştir.
//	
//}

/*--------------------------------------------------------------------------------------------------------------------------------------------------------------
	Bir nesne başvurusu bir yönteme geçirildiğinde, başvurunun kendisi call-by-value kullanılarak geçirilir.
	Ancak, iletilen değer bir nesneye başvurduğundan, bu değerin kopyası yine de karşılık gelen bağımsız değişkenin yaptığı aynı nesneye başvurur.
--------------------------------------------------------------------------------------------------------------------------------------------------------------*/




/*--------------------------------------------------------------------------------------------------------------------------------------------------------------
		Returning Objects
		
	Bir yöntem, oluşturduğunuz sınıf türleri de dahil olmak üzere her türlü veriyi döndürebilir.
	Örneğin, aşağıdaki programda, incrByTen( ) yöntemi, a değerinin 10 dan büyük olduğu nesneyi döndürür.

--------------------------------------------------------------------------------------------------------------------------------------------------------------*/

//class Test
//{
//	int a;
//	
//	Test(int i)
//	{
//		a = i;
//	}
//	
//	Test incrByTen()
//	{
//		Test temp = new Test(a + 10);
//		return temp;
//	}
//}

//class RetOb
//{
//	public static void main(String[] args) 
//	{
//		Test ob1 = new Test(2);
//		Test ob2;
//		
//		ob2 = ob1.incrByTen();
//		
//		System.out.println("ob1.a: " + ob1.a);
//		System.out.println("ob2.a: " + ob2.a);
//		
//		ob2 = ob2.incrByTen();
//		System.out.println("ob2.a after second increase: "+ ob2.a);
//	}
//	
//	
//	//The output generated by this program is shown here:
//	//ob1.a: 2
//	//ob2.a: 12
//	//ob2.a after second increase: 22
//}


/*--------------------------------------------------------------------------------------------------------------------------------------------------------------
  
  Gördüğünüz gibi, incrByTen( ) her çağrıldığında, yeni bir nesne oluşturulur ve buna bir başvuru çağırma yordamına döndürülür.
  Önceki program başka bir önemli noktayı ortaya koymaktadır: Tüm nesneler dinamik olarak new kullanılarak tahsis edildiğinden, bir nesnenin kapsam dışına çıkması konusunda endişelenmenize gerek yoktur.
  oluşturulduğu yöntem sona erdiği için bir nesnenin kapsam dışına çıkması konusunda endişelenmenize gerek yoktur. 
  Nesne, programınızın herhangi bir yerinde kendisine bir başvuru olduğu sürece var olmaya devam edecektir. 
  Buna herhangi bir referans olmadığında, nesne bir dahaki sefere çöp toplama gerçekleştiğinde geri kazanılır.
  
--------------------------------------------------------------------------------------------------------------------------------------------------------------*/




/*--------------------------------------------------------------------------------------------------------------------------------------------------------------
  				Recursion
  	
  	Java recursion destekler. 
  	Recursion, bir şeyi kendisi açısından tanımlama sürecidir. 
  	Java programlama ile ilgili olarak, recursion, bir yöntemin kendisini çağırmasına izin veren niteliktir. 
  	Kendini çağıran bir yöntemin recursion olduğu söylenir.	
  		
  	Özyinelemenin klasik örneği, bir sayının faktöriyelinin hesaplanmasıdır. 
  	Bir N sayısının faktöriyeli, 1 ile N arasındaki tüm tam sayıların ürünüdür. 
  	Örneğin, 3 faktöriyel 1 × 2 × 3 × veya 6'dır. 
  	Bir faktöriyelin özyinelemeli bir yöntem kullanılarak nasıl hesaplanabileceği aşağıda açıklanmıştır:
--------------------------------------------------------------------------------------------------------------------------------------------------------------*/

//class Factorial
//{
//	int fact(int n)
//	{
//		int result;
//		
//		if(n==1) return 1;
//		result = fact(n-1) * n;
//		return result;
//	}
//}
//
//class Recursion
//{
//	public static void main(String[] args) 
//	{
//		Factorial f = new Factorial();
//		
//		System.out.println("Factorial of 3 is " + f.fact(3));
//		System.out.println("Factorial of 4 is " + f.fact(4));
//		System.out.println("Factorial of 4 is " + f.fact(5));
//	}
//	
//	//The output from this program is shown here:
//	//Factorial of 3 is 6
//	//Factorial of 4 is 24
//	//Factorial of 5 is 120
//}


/*--------------------------------------------------------------------------------------------------------------------------------------------------------------
 	Özyinelemeli yöntemlere aşina değilseniz, fact( ) işleminin çalışması biraz kafa karıştırıcı görünebilir. 
 	İşte nasıl çalıştığı.
 	fact( ) 1 bağımsız değişkeniyle çağrıldığında, işlev 1 değerini döndürür; aksi takdirde, fact(n–1)*n ürününü döndürür
 	Bu ifadeyi değerlendirmek için, fact( ) n–1 ile çağrılır. Bu işlem, n 1'e eşit olana kadar tekrarlanır ve yönteme yapılan çağrılar geri dönmeye başlar.
 	
 	fact( ) yönteminin nasıl çalıştığını daha iyi anlamak için kısa bir örnek verelim. 3'ün faktöriyeli değerini hesapladığınızda, fact( )'e yapılan ilk çağrı, 2'lik bir argümanla ikinci bir çağrının yapılmasına neden olur. 
 	Bu çağrı, fact( )'in 1 argümanıyla üçüncü kez çağrılmasına neden olacaktır. 
 	
 --------------------------------------------------------------------------------------------------------------------------------------------------------------*/

/*--------------------------------------------------------------------------------------------------------------------------------------------------------------
 		Introducing Access Control
    
	Bildiğiniz gibi, kapsülleme verileri onu işleyen kodla ilişkilendirir. 
	Ancak, kapsülleme başka bir önemli özellik sağlar: erişim denetimi. 
	Kapsülleme yoluyla, bir programın hangi bölümlerinin bir sınıfın üyelerine erişebileceğini denetleyebilirsiniz. 
	Erişimi denetleyerek kötüye kullanımı önleyebilirsiniz.
    
    
    Bir üyeye nasıl erişilebileceği, beyannamesine bağlı erişim değiştirici tarafından belirlenir. Java, zengin bir erişim değiştirici seti sağlar. 
    Erişim kontrolünün bazı yönleri çoğunlukla miras veya paketlerle ilgilidir. (Bir paket, esasen bir grup sınıftır.)
    
    Java’nın erişim kontrol mekanizmasının bu bölümleri sonraki bölümlerde tartışılacaktır. 
    Burada, tek bir sınıf için geçerli olduğu için erişim kontrolünü inceleyerek başlayalım. 
    Erişim kontrolünün temellerini anladıktan sonra, geri kalanı kolay olacaktır.

	Java'nın erişim değiştiricileri public, private ve protected. 
	Java ayrıca varsayılan erişim düzeyini de tanımlar.
	protected yalnızca miras söz konusu olduğunda geçerlidir. 
	Diğer erişim değiştiriciler aşağıda açıklanmıştır.
	
	public ve private tanımlayarak başlayalım.
	
	Bir sınıfın üyesi public olarak belirtildiğinde, bu üyeye başka bir kod tarafından erişilebilir.
	Bir sınıfın üyesi private olarak belirtildiğinde, bu üyeye yalnızca sınıfının diğer üyeleri erişebilir.
	
	public ve private erişimin etkilerini anlamak için, aşağıdaki programı göz önünde bulundurun:
--------------------------------------------------------------------------------------------------------------------------------------------------------------*/


//class Test
//{
//	int a; //default acces
//	public int b; //public acces
//	private int c; //private acces
//	
//	//methods to acces c
//	void setc(int i)
//	{
//		c = i;
//	}
//	
//	int getc()
//	{
//		return c;
//	}
//}
//
//class AccesTest
//{
//	public static void main(String[] args) 
//	{
//		Test ob = new Test();
//		
//		ob.a = 10;
//		ob.b = 20;
//		
//		ob.c = 100; //error
//		
//		ob.setc(100);
//		
//		System.out.println("a, b, and c: " + ob.a + " " +
//				 ob.b + " " + ob.getc());
//		
//		
//	}
//}

/*--------------------------------------------------------------------------------------------------------------------------------------------------------------
 	Gördüğünüz gibi, Test sınıfı içinde a, bu örnek için public'i belirtmekle aynı olan varsayılan erişimi kullanır.
 	 b açıkça herkese açık olarak belirtilir(public).
 	 c private olarak belirtilir.
 	 
 	 Bu, sınıfının dışındaki kodla erişilemeyeceği anlamına gelir. 
 	 Bu nedenle, AccessTest sınıfı içinde, c doğrudan kullanılamaz.
 	 
 	 Erişim denetiminin daha pratik bir örneğe nasıl uygulanabileceğini görmek için,aşağıdaki örneğe bakalım
--------------------------------------------------------------------------------------------------------------------------------------------------------------*/


//class Stack
//{
//	
//	private int[] stck = new int[10];
//	private int tos;
//	
//	Stack()
//	{
//		tos = -1;
//	}
//	
//	void push(int item)
//	{
//		if(tos == 9)
//			System.out.println("Stack is full");
//		else
//			stck[++tos] = item;
//	}
//	
//	int pop()
//	{
//		if(tos < 0)
//		{
//			System.out.println("Stack underflow.");
//			return 0;
//		}
//		else
//			return stck[tos--];
//	}
//}


/*--------------------------------------------------------------------------------------------------------------------------------------------------------------
  	Gördüğünüz gibi, şimdi hem yığını tutan stck hem de yığının üst kısmının dizini olan tos private olarak belirtiliyor.
  	Bu, push( ) ve pop( ) dışında erişilemeyecekleri veya değiştirilemeyecekleri anlamına gelir.
  	Örneğin, tos'u private yapmak, programınızın diğer bölümlerinin yanlışlıkla stck dizisinin sonunun ötesinde bir değere ayarlamasını önler.
  	Aşağıdaki programda geliştirilmiş Stack sınıfı gösterilmektedir. 
  	Stck ve tos üyelerinin gerçekten erişilemez olduğunu kendinize kanıtlamak için yorumlanmış satırları kaldırmayı deneyin.
--------------------------------------------------------------------------------------------------------------------------------------------------------------*/

//class TestStack
//{
//	public static void main(String[] args) 
//	{
//		Stack mystack1 = new Stack();
//		Stack mystack2 = new Stack();
//		
//		for(int i = 0; i < 10; i++) mystack1.push(i);
//		for(int i=10; i<20; i++) mystack2.push(i);
//		
//		// pop those numbers off the stack
//		 System.out.println("Stack in mystack1:");
//		 for(int i=0; i<10; i++)
//		 System.out.println(mystack1.pop());
//		 
//		 
//		 System.out.println("Stack in mystack2:");
//		 for(int i=0; i<10; i++)
//		 System.out.println(mystack2.pop());
//		 // these statements are not legal
//		 // mystack1.tos = -2;
//		 // mystack2.stck[3] = 100;
//	}
//}



/*--------------------------------------------------------------------------------------------------------------------------------------------------------------
					Understanding static
	
	Bir sınıfın herhangi bir nesnesinden bağımsız olarak kullanılacak bir sınıf üyesi tanımlamak isteyeceğiniz zamanlar olacaktır.
	Normalde, bir sınıf üyesine yalnızca sınıfının bir nesnesiyle birlikte erişilmelidir.
	Ancak, belirli bir örneğe başvurmadan kendi başına kullanılabilecek bir üye oluşturmak mümkündür.
	Böyle bir üye oluşturmak için, bildiriminden önce statik anahtar sözcüğünü kullanın. 
	Bir üye statik olarak bildirildiğinde, sınıfının herhangi bir nesnesi oluşturulmadan önce ve herhangi bir nesneye başvurulmadan üyeye erişilebilir.
	Hem yöntemleri hem de değişkenleri statik olarak bildirebilirsiniz.
	
	Statik üyenin en yaygın örneği main( ) şeklindedir. main( ) statik olarak bildirilir, çünkü herhangi bir nesne var olmadan önce çağrılması gerekir.
	Statik olarak bildirilen örnek değişkenleri esasen global değişkenlerdir.
	Sınıfının nesneleri bildirildiğinde, statik değişkenin kopyası oluşturulmaz.
	Bunun yerine, sınıfın tüm örnekleri aynı statik değişkeni paylaşır.
	
	Statik olarak bildirilen yöntemlerin birkaç kısıtlaması vardır:
		• Sadece sınıflarının diğer statik yöntemlerini doğrudan çağırabilirler.
		• Sadece sınıflarının statik değişkenlerine doğrudan erişebilirler.
		• this veya super ile hiçbir şekilde atıfta bulunamazlar. (Süper anahtar kelimesi devralmayla ilgilidir ve bir sonraki bölümde açıklanmıştır.)
	
	
	Statik değişkenlerinizi initialize etmek için hesaplama yapmanız gerekiyorsa, sınıf ilk yüklendiğinde tam olarak bir kez yürütülen statik bir blok bildirebilirsiniz. 
	Aşağıdaki örnekte statik yöntem, bazı statik değişkenler ve staticinitialization bloğu olan bir sınıf gösterilmektedir:
--------------------------------------------------------------------------------------------------------------------------------------------------------------*/



//class UseStatic
//{
//	static int a = 3;
//	static int b ;
//	
//	static void meth(int x)
//	{
//		System.out.println("x = " + x);
//		System.out.println("a = " + a);
//		System.out.println("b = " + b);
//	}
//	
//	static 
//	{
//		System.out.println("Static bloc kinitialized.");
//		b = a*4;
//	}
//	
//	public static void main(String[] args) {
//		 meth(42);
//	}
//}


/*--------------------------------------------------------------------------------------------------------------------------------------------------------------
	UseStatic sınıfı yüklenir yüklenmez, tüm statik deyimler çalıştırılır.
	İlk olarak, a 3 olarak ayarlanır, ardından statik blok yürütülür, bu da bir mesajı yazdırır ve ardından b'yi a * 4 veya 12'ye başlatır.
	
	Tanımlandıkları sınıfın dışında, statik yöntemler ve değişkenler herhangi bir nesneden bağımsız olarak kullanılabilir. 
	Bunu yapmak için, yalnızca sınıflarının adını ve ardından nokta işlecini belirtmeniz gerekir. 
	Örneğin, statik bir yöntemi sınıfının dışından çağırmak isterseniz, bunu aşağıdaki genel formu kullanarak yapabilirsiniz:
	
	classname.method()
	
	

--------------------------------------------------------------------------------------------------------------------------------------------------------------*/




/*--------------------------------------------------------------------------------------------------------------------------------------------------------------
 			Introducing final
 	
 	 Bir field final olarak bildirilebilir. 
 	 Bunu yapmak, içeriğinin değiştirilmesini önler ve esasen sabit hale getirir. 
 	 Bu, final bir alanı bildirildiğinde initialize gerektiği anlamına gelir.	
 	 Bunu iki yoldan biriyle yapabilirsiniz: İlk olarak, bildirildiğinde ona bir değer verebilirsiniz.
 	 İkinci olarak, bir oluşturucu içinde bir değer atayabilirsiniz. İlk yaklaşım muhtemelen en yaygın olanıdır. İşte bir örnek:
 	 
 	 final int FILE_NEW = 1;
	 final int FILE_OPEN = 2;
	 final int FILE_SAVE = 3;
	 final int FILE_SAVEAS = 4;
	 final int FILE_QUIT = 5;
	 
	 Programınızın sonraki bölümleri artık FILE_OPEN vb., sanki sabitlermiş gibi, bir değerin değiştirildiğinden korkmadan kullanabilir. 
	 Bu örnekte gösterildiği gibi, son alanlar için tüm büyük harf tanımlayıcılarını seçmek yaygın bir kodlama kuralıdır.
	 
	 Alanlara ek olarak, hem yöntem parametreleri hem de yerel değişkenler final olarak bildirilebilir. 
	 Bir parametrenin final bildirilmesi, yöntem içinde değiştirilmesini engeller. ,
	 Yerel değişken final bildirilmesi, ona birden çok kez değer atanmasını engeller.
	 final anahtar kelimesi yöntemlere de uygulanabilir, ancak anlamı değişkenlere uygulandığından önemli ölçüde farklıdır. Finalin bu ek kullanımı, kalıtım açıklandığında bir sonraki bölümde açıklanmaktadır.
	 
--------------------------------------------------------------------------------------------------------------------------------------------------------------*/


/*--------------------------------------------------------------------------------------------------------------------------------------------------------------
 			Arrays Revisited
 	
 	Diziler bu kitabın başlarında, sınıflar tartışılmadan önce tanıtıldı. 
 	Artık sınıflar hakkında bilgi sahibi olduğunuza göre, diziler hakkında önemli bir noktaya değinilebilir: nesneler olarak uygulanırlar. 
 	Bu nedenle, yararlanmak isteyeceğiniz özel bir dizi özniteliği vardır. 
 	Özellikle, bir dizinin boyutu, yani bir dizinin tutabileceği öğe sayısı, uzunluk örneği değişkeninde bulunur. 
 	Tüm diziler bu değişkene sahiptir ve her zaman dizinin boyutunu tutar. 
 	İşte bu özelliği gösteren bir program:
--------------------------------------------------------------------------------------------------------------------------------------------------------------*/

//class Length
//{
//	public static void main(String[] args) 
//	{
//		int[] a1 = new int[10];
//		int[] a2 = {3,5,7,8,99,44,-10};
//		int[] a3 = {4,3,2,1};
//		
//		System.out.println("length of a1 is " + a1.length);
//		System.out.println("length of a2 is " + a2.length);
//		System.out.println("length of a3 is " + a3.length);
//	}
//}

/*--------------------------------------------------------------------------------------------------------------------------------------------------------------
	Gördüğünüz gibi, her dizinin boyutu görüntülenir. 
	Uzunluğun değerinin, gerçekte kullanımda olan öğelerin sayısıyla hiçbir ilgisi olmadığını unutmayın. 
	Yalnızca dizinin tutmak üzere tasarlandığı öğe sayısını yansıtır.
	
	Uzunluk üyesini birçok durumda iyi bir şekilde kullanabilirsiniz. 
	Örneğin, burada Stack sınıfının geliştirilmiş bir sürümü verilmiştir. 
	Hatırlayabileceğiniz gibi, bu sınıfın önceki sürümleri her zaman on öğeli bir yığın oluşturuyordu. 
	Aşağıdaki sürüm, herhangi bir boyutta yığınlar oluşturmanıza olanak tanır. stck.length değeri, yığının taşmasını önlemek için kullanılır.
	
--------------------------------------------------------------------------------------------------------------------------------------------------------------*/


//class Stack
//{
//	private int[] stck;
//	private int tos;
//	
//	Stack(int size)
//	{
//		stck = new int[size];
//		tos = -1;
//	}
//	
//	void push(int item)
//	{
//		if(tos == stck.length-1)
//			System.out.println("Stack is full.");
//		else
//			stck[++tos] = item;
//	}
//	
//	int pop()
//	{
//		if (tos < 0)
//		{
//			System.out.println("Stack underflow.");
//			return 0;
//		}
//		else
//			return stck[tos--];
//	}
//}
//
//class TestStack2
//{
//	public static void main(String[] args) 
//	{
//		Stack mystack1 = new Stack(5);
//		Stack mystack2 = new Stack(8);
//		
//		for(int i=0; i<5; i++) mystack1.push(i);
//		for(int i=0; i<8; i++) mystack2.push(i);
//		
//		// pop those numbers off the stack
//		System.out.println("Stack in mystack1:");
//		for(int i=0; i<5; i++)
//			System.out.println(mystack1.pop());
//		
//		System.out.println("Stack in mystack2:");
//		for(int i=0; i<8; i++)
//			System.out.println(mystack2.pop());
//		
//		
//	}
//}

/*--------------------------------------------------------------------------------------------------------------------------------------------------------------
	Programın iki yığın oluşturduğuna dikkat edin: biri beş öğe derinliğinde, diğeri sekiz öğe derinliğinde. 
	Gördüğünüz gibi, dizilerin kendi uzunluk bilgilerini korumaları, herhangi bir boyutta yığın oluşturmayı kolaylaştırır.
	--------------------------------------------------------------------------------------------------------------------------------------------------------------*/




/*--------------------------------------------------------------------------------------------------------------------------------------------------------------
		Introducing Nested and Inner Classes
		
	Bir sınıfı başka bir sınıf içinde tanımlamak mümkündür; bu tür sınıflar iç içe geçmiş sınıflar olarak bilinir.(nested class)
	İç içe geçmiş bir sınıfın kapsamı, çevreleyen sınıfının kapsamı ile sınırlıdır.
	Dolayısıyla, eğer B sınıfı A sınıfı içinde tanımlanmışsa, o zaman B, A'dan bağımsız olarak var olmaz.
	İç içe geçmiş bir sınıfın, içinde bulunduğu sınıfın private üyeleri de dahil olmak üzere üyelerine erişimi vardır.
	
	Ancak, çevreleyen sınıfın iç içe geçmiş sınıfın üyelerine erişimi yoktur.
	Doğrudan kendi çevreleyen sınıf kapsamı içinde bildirilen iç içe geçmiş bir sınıf, onun çevreleyen sınıfının bir üyesidir.
	Bir bloğa yerel olan iç içe geçmiş bir sınıf bildirmek de mümkündür.
	
	İki tür iç içe geçmiş sınıf vardır: statik ve statik olmayan. 
	Statik iç içe geçmiş sınıf, statik değiştiricinin uygulandığı sınıftır. 
	Statik olduğundan, çevreleyen sınıfının statik olmayan üyelerine bir nesne aracılığıyla erişmesi gerekir.
	
	Yani, doğrudan kapsayan sınıfının statik olmayan üyelerine atıfta bulunamaz.
	
	İkinci tür iç içe geçmiş sınıf iç sınıftır. 
	İç sınıf, statik olmayan iç içe geçmiş bir sınıftır. 
	Dış sınıfının tüm değişkenlerine ve yöntemlerine erişebilir ve bunlara doğrudan dış sınıfın diğer statik olmayan üyelerinin yaptığı gibi başvurabilir.
	
	Aşağıdaki program, bir iç sınıfın nasıl tanımlanacağını ve kullanılacağını gösterir. 
	Outer adlı sınıfın outer_x adında bir örnek değişkeni, test( ) adlı bir örnek yöntemi vardır ve Inner adlı bir iç sınıfı tanımlar.
	
--------------------------------------------------------------------------------------------------------------------------------------------------------------*/

//class Outer
//{
//	int outer_x = 100;
//	
//	void test()
//	{
//		Inner inner = new Inner();
//		inner.display();
//	}
//	
//	class Inner
//	{
//		void display()
//		{
//			System.out.println("display: outer_x = "+ outer_x);
//		}
//	}
//}
//
//class InnerClassDemo
//{
//	public static void main(String[] args) 
//	{
//		Outer outer = new Outer();
//		outer.test();
//	}
//}

/*--------------------------------------------------------------------------------------------------------------------------------------------------------------
 	Programda, Outer sınıfı kapsamında Inner adında bir iç sınıf tanımlanmıştır. 
 	Bu nedenle, Inner sınıfındaki herhangi bir kod, outer_x değişkene doğrudan erişebilir.
 	display( ) adlı bir örnek yöntemi Inner içinde tanımlanır. 
 	Bu yöntem, standart çıkış akışındaki outer_x görüntüler.
 	
 	InnerClassDemo'nun main( ) yöntemi, Outer sınıfının bir örneğini oluşturur ve test( ) yöntemini çağırır. 
 	Bu yöntem, Inner sınıfının bir örneğini oluşturur ve display( ) yöntemi çağrılır.
 	
 	İçsel'in bir örneğinin yalnızca sınıf Dışsal bağlamında yaratılabileceğini anlamak önemlidir. 
 	Java derleyicisi aksi takdirde bir hata iletisi oluşturur. 
 	Genel olarak, bir iç sınıf örneği, örnekte olduğu gibi, genellikle kendi çevreleyen kapsamı içindeki kod tarafından oluşturulur.
 	
 	Açıklandığı gibi, bir iç sınıf, çevreleyen sınıfın tüm üyelerine erişebilir, ancak bunun tersi doğru değildir. 
 	İç sınıfın üyeleri yalnızca iç sınıf kapsamında bilinir ve dış sınıf tarafından kullanılamaz. Örnek
 	
--------------------------------------------------------------------------------------------------------------------------------------------------------------*/

//class Outer
//{
//	int outer_x = 100;
//	
//	void test()
//	{
//		Inner inner = new Inner();
//		inner.display();
//	}
//	
//	class Inner
//	{
//		int y = 10;
//		
//		void display()
//		{
//			System.out.println("display: outer_x = "+ outer_x);
//		}
//	}
//	
//	void showy()
//	{
//		System.out.println(y); //error, y not known here
//	}
//}
//
//class InnerClassDemo
//{
//	public static void main(String[] args) 
//	{
//		Outer outer = new Outer();
//		outer.test();
//	}
//}



/*--------------------------------------------------------------------------------------------------------------------------------------------------------------
 	Burada y, Inner'ın bir örnek değişkeni olarak bildirilir. 
 	Bu nedenle, o sınıfın dışında bilinmemektedir ve showy( ) tarafından kullanılamaz.
 	
  	Her ne kadar bir dış sınıf kapsamında üye olarak ilan edilen iç sınıflara odaklanmış olsak da, herhangi bir blok kapsamında iç sınıfları tanımlamak mümkündür.
  	Örneğin, bir yöntemle tanımlanan blok içinde veya hatta bir for döngüsünün gövdesi içinde, bu sonraki programın gösterdiği gibi, iç içe geçmiş bir sınıf tanımlayabilirsiniz:
  	--------------------------------------------------------------------------------------------------------------------------------------------------------------*/

//class Outer
//{
//	int outer_x = 100;
//	
//	void test()
//	{
//		for (int i = 0; i < 10; i++)
//		{
//			class Inner {
//				void display()
//				{
//					System.out.println("display, outer_x = "+ outer_x);
//				}
//			}
//			Inner inner = new Inner();
//			inner.display();
//		}
//	}
//}
//
//class InnerClassDemo
//{
//	public static void main(String[] args) 
//	{
//		Outer outer = new Outer();
//		outer.test();
//	}
//}



/*--------------------------------------------------------------------------------------------------------------------------------------------------------------
 	İç içe geçmiş sınıflar tüm durumlara uygulanamasa da, olayları işlerken özellikle yararlıdırlar.
 	Bölüm 25'te iç içe geçmiş sınıflar konusuna döneceğiz. 
 	Burada, belirli olay türlerini işlemek için gereken kodu basitleştirmek için iç sınıfların nasıl kullanılabileceğini göreceksiniz.
 	Ayrıca, adı olmayan iç sınıflar olan anonim iç sınıflar hakkında da bilgi edineceksiniz.
 	İlgi çekici bir nokta: Java için orijinal 1.0 belirtimi tarafından iç içe geçmiş sınıflara izin verilmemiştir. Java 1.1 tarafından eklenmiştir.
 	
 --------------------------------------------------------------------------------------------------------------------------------------------------------------*/



/*--------------------------------------------------------------------------------------------------------------------------------------------------------------
 			Exploring the String Class

 	Her ne kadar String sınıfı bu kitabın II. Bölümünde derinlemesine incelenecek olsa da, 
 	şimdi kısa bir araştırması yapılması gerekiyor, çünkü Bölüm I'in sonuna doğru gösterilen örnek programların bazılarında dizeler kullanacağız.
 	String muhtemelen Java'nın sınıf kitaplığında en sık kullanılan sınıftır. 
 	Bunun bariz nedeni, dizelerin programlamanın çok önemli bir parçası olmasıdır.
 	
 	Dizeler hakkında anlaşılması gereken ilk şey, oluşturduğunuz her dizenin aslında String türünde bir nesne olduğudur. 
 	Dize sabitleri bile aslında String nesneleridir. Örneğin, ifadede
 	System.out.println("This is a String, too");
 	'This is a String, too' dizesi bir String nesnesidir.
 	
 	Dizeler hakkında anlaşılması gereken ikinci şey, String türündeki nesnelerin değişmez olmasıdır; Bir String nesnesi oluşturulduktan sonra, içeriği değiştirilemez.
 	
 	Bu ciddi bir kısıtlama gibi görünse de, iki nedenden dolayı değildir:
 		
 		Bir dizeyi değiştirmeniz gerekirse, her zaman değişiklikleri içeren yeni bir dize oluşturabilirsiniz.
 		
 		Java, dizelerin değiştirilmesine izin veren StringBuffer ve StringBuilder adlı String'in eş sınıflarını tanımlar, böylece tüm normal dize manipülasyonları Java'da hala kullanılabilir.
 	
 	Dizeler çeşitli şekillerde oluşturulabilir. En kolay olanı şöyle bir ifade kullanmaktır:
 		String myString = "this is a test";
 		
 	
 	Bir String nesnesi oluşturduktan sonra, dizeye izin verilen her yerde kullanabilirsiniz. Örneğin, bu deyim myString'i görüntüler:
 		System.out.println(myString)
 		
 	Java, String nesneleri için bir işleç tanımlar: + İki dizeyi birleştirmek için kullanılır. Örneğin, bu ifade
 		String myString = "I" + " like " + "Java.";
 			
--------------------------------------------------------------------------------------------------------------------------------------------------------------*/

//class StringDemo
//{
//	public static void main(String[] args)
//	{
//		String strOb1 = "First String";
//		String strOb2 = "Second String";
//		String strOb3 = strOb1 + " and " + strOb2;
//		
//		System.out.println(strOb1);
//		System.out.println(strOb2);
//		System.out.println(strOb3);
//		
//	}
//}

/*--------------------------------------------------------------------------------------------------------------------------------------------------------------
	String sınıfı, kullanabileceğiniz birkaç yöntem içerir. İşte birkaçı. 
	equals( ) komutunu kullanarak iki dizeyi eşitlik açısından sınayabilirsiniz.
	
	Bir dizenin uzunluğunu, length( ) yöntemini çağırarak elde edebilirsiniz.
	charAt( ) öğesini çağırarak bir dize içindeki belirli bir dizindeki karakteri elde edebilirsiniz.
	
	The general forms of these three methods are shown here:
	
	boolean equals(secondStr)
	int length( )
	char charAt(index)

--------------------------------------------------------------------------------------------------------------------------------------------------------------*/

//class StringDemo2
//{
//	public static void main(String[] args) 
//	{
//		String strOb1 = "First String";
//		String strOb2 = "Second String";
//		String strOb3 = strOb1;
//		
//		System.out.println("Length of strOb1: " +
//				 strOb1.length());
//		
//		System.out.println("Char at index 3 in strOb1: " +
//				 strOb1.charAt(3));
//		
//		if(strOb1.equals(strOb2))
//			 System.out.println("strOb1 == strOb2");
//		else
//			 System.out.println("strOb1 != strOb2");
//		if(strOb1.equals(strOb3))
//			 System.out.println("strOb1 == strOb3");
//		else
//			 System.out.println("strOb1 != strOb3");
//	}
//}

/*--------------------------------------------------------------------------------------------------------------------------------------------------------------
	Tabii ki, dize dizilerine sahip olabilirsiniz, tıpkı başka herhangi bir nesne türünün dizilerine sahip olabileceğiniz gibi. Mesela:
--------------------------------------------------------------------------------------------------------------------------------------------------------------*/

//class StringDemo3 
//{
//	public static void main(String[] args) 
//	{
//		String[] str = {"one","two","three"};
//		
//		for(int i = 0; i< str.length; i++)
//			System.out.println("str["+i+"]: " + str[i]);
//	}
//}


/*--------------------------------------------------------------------------------------------------------------------------------------------------------------
		Using Command-Line Arguments
	
		Bazen bir programı çalıştırdığınızda programa bilgi aktarmak isteyebilirsiniz.
		Bu, komut satırı bağımsız değişkenlerini main( ) öğesine geçirerek gerçekleştirilir.
		Komut satırı bağımsız değişkeni, yürütüldüğü sırada komut satırında programın adını doğrudan izleyen bilgilerdir. 
		Bir Java programı içindeki komut satırı bağımsız değişkenlerine erişmek oldukça kolaydır, bunlar main( ) öğesinin args parametresine iletilen bir String dizisinde dize olarak saklanır.
		
		İlk komut satırı bağımsız değişkeni args[0], ikincisi args[1] vb. konumunda depolanır. Örneğin, aşağıdaki program çağrıldığı tüm komut satırı bağımsız değişkenlerini görüntüler:
		
		Tüm komut satırı bağımsız değişkenleri dize olarak geçirilir.
--------------------------------------------------------------------------------------------------------------------------------------------------------------*/

//class CommandLine
//{
//	public static void main(String[] args) 
//	{
//		for(int i = 0; i < args.length; i++)
//			System.out.println("args[" + i + "]: " +
//					 args[i]);
//	}
//
//}


/*--------------------------------------------------------------------------------------------------------------------------------------------------------------
			Varargs: Variable-Length Arguments
	
	Java'nın modern sürümleri, değişken sayıda bağımsız değişken alması gereken yöntemlerin oluşturulmasını basitleştiren bir özellik içerir.
	
	Bu özelliğe varargs denir ve variable length arguments değişkenlerin kısaltmasıdır. 
	Değişken sayıda bağımsız değişken alan bir yönteme varargs yöntemi veya basitçe varargs yöntemi denir.
	
	Bir yönteme değişken sayıda bağımsız değişkenin geçirilmesini gerektiren durumlar olağandışı değildir.
	Örneğin, Internet bağlantısı açan bir yöntem kullanıcı adı, parola, dosya adı, protokol vb. alabilir, ancak bu bilgilerin bazıları sağlanmazsa varsayılanları sağlayabilir.
	Bu durumda, yalnızca varsayılanların geçerli olmadığı bağımsız değişkenleri geçirmek uygun olacaktır.
	
	Java'nın ilk günlerinde, değişken uzunluktaki argümanlar, ikisi de özellikle hoş olmayan iki şekilde ele alınabiliyordu.
	
	İlk olarak, maximum değişken sayısı küçükse ve biliniyorsa, 
	yöntemin çağrılabileceği her yol için bir tane olmak üzere yöntemin aşırı yüklenmiş sürümlerini oluşturabilirsiniz.
	
	Bu işe yarasa ve bazı durumlar için uygun olsa da, yalnızca dar bir durum sınıfı için geçerlidir.
	
	En fazla sayıda potansiyel bağımsız değişkenin daha büyük olduğu veya bilinemediği durumlarda, bağımsız değişkenlerin bir diziye yerleştirildiği ve ardından dizinin yönteme geçirildiği ikinci bir yaklaşım kullanılmıştır.
	
	Eski eski kodda hala bulabileceğiniz bu yaklaşım, aşağıdaki program tarafından gösterilmiştir:
	
--------------------------------------------------------------------------------------------------------------------------------------------------------------*/

//class PassArray
//{
//	static void vaTest(int[] v)
//	{
//		System.out.print("number of args " + v.length + "contetns:");
//		
//		for(int x : v)
//			System.out.print(x + "");
//		System.out.println();
//	}
//	
//	public static void main(String[] args) 
//	{
//		int[] n1 = {10};
//		int[] n2 = {1,2,3};
//		int[] n3 = {};
//		
//		vaTest(n1); //1arg
//		vaTest(n2); //3arg
//		vaTest(n3); //no arg
//	}
//}


/*--------------------------------------------------------------------------------------------------------------------------------------------------------------
	Programda, vaTest( ) yöntemi bağımsız değişkenlerini v dizisi üzerinden geçirir.
	Değişken uzunluktaki bağımsız değişkenlere yönelik bu eski tarz yaklaşım, vaTest( )'nin rasgele sayıda bağımsız değişken almasını sağlar.
	Ancak, bu bağımsız değişkenlerin vaTest( ) öğesini çağırmadan önce bir diziye el ile paketlenmesini gerektirir.
	VaTest( ) her çağrıldığında bir dizi oluşturmak sadece sıkıcı olmakla kalmaz, aynı zamanda potansiyel olarak hataya açıktır. 
	Varargs özelliği daha basit ve daha iyi bir seçenek sunar.
	
	Değişken uzunluk bağımsız değişkeni üç nokta (...) ile belirtilir. Örneğin, vaTest( ) bir vararg kullanılarak nasıl yazılır:
		static void vaTest(int ... v) {
	
	Bu sözdizimi, derleyiciye vaTest( ) öğesinin sıfır veya daha fazla bağımsız değişkenle çağrılabileceğini söyler. 
	Sonuç olarak, v dolaylı olarak int[ ] türünde bir dizi olarak bildirilir.
	
	Böylece, vaTest( ), içinde v'ye normal dizi sözdizimi kullanılarak erişilir. 
	İşte bir vararg kullanılarak yeniden yazılmış önceki program:
--------------------------------------------------------------------------------------------------------------------------------------------------------------*/

//class VarArgs
//{
//	
//	static void vaTest(int... v)
//	{
//		System.out.print("Number of args: " + v.length + "Contents: ");
//		
//		for(int x:v)
//			System.out.println(x + " ");
//		
//		System.out.println();
//	}
//	
//	public static void main(String[] args) 
//	{
//		vaTest(10);
//		vaTest(1,2,3);
//		vaTest();
//	}
//}

/*--------------------------------------------------------------------------------------------------------------------------------------------------------------
	Programdan alınan çıktı orijinal sürümle aynıdır.
	Bu program hakkında dikkat edilmesi gereken iki önemli şey var. 
	İlk olarak, açıklandığı gibi, vaTest( ), içinde v bir dizi olarak çalıştırılır.
	
	Bunun nedeni, v'nin bir dizi olmasıdır. 
	Bu ... sözdizimi basitçe derleyiciye değişken sayıda bağımsız değişken kullanılacağını ve bu bağımsız değişkenlerin v tarafından başvurulan dizide depolanacağını söyler. 
	
	İkincisi, main( ), vaTest( ), hiç argüman içermeyen, farklı sayıda argümanla çağrılır.
	
	Bağımsız değişkenler otomatik olarak bir diziye yerleştirilir ve v'ye geçirilir. 
	Bağımsız değişken olmaması durumunda, dizinin uzunluğu sıfırdır.
	
	Bir yöntem, değişken uzunluklu bir parametreyle birlikte \"normal\" parametrelere sahip olabilir. 
	Ancak, değişken uzunluk parametresi, yöntem tarafından bildirilen son parametre olmalıdır. 
	Örneğin, bu yöntem bildirimi tamamen kabul edilebilir:
	
		int doIt(int a, int b, double c, int ... vals) {
	
	Bu durumda, doIt( ) çağrısında kullanılan ilk üç bağımsız değişken ilk üç parametreyle eşleştirilir.
	Daha sonra, kalan bağımsız değişkenlerin vals'e ait olduğu varsayılır.
	
	Unutmayın, varargs parametresi en son olmalıdır. Örneğin, aşağıdaki bildirim yanlıştır:
		
		int doIt(int a, int b, double c, int ... vals, boolean stopFlag) { // Error!
	Burada, yasadışı olan varargs parametresinden sonra düzenli bir parametre bildirme girişimi vardır.
	
	Dikkat edilmesi gereken bir kısıtlama daha vardır: yalnızca bir varargs parametresi olmalıdır. 
	Örneğin, bu bildirim de geçersizdir:
	
	int doIt(int a, int b, double c, int ... vals, double ... morevals) { // Error!
	İkinci varargs parametresini bildirme girişimi yasa dışıdır.
	
	
	İşte düzenli bir bağımsız değişken ve değişken uzunlukta bir bağımsız değişken alan vaTest( ) yönteminin yeniden çalışılmış bir sürümü:
--------------------------------------------------------------------------------------------------------------------------------------------------------------*/

//class VarArgs2
//{
//	
//	static void vaTest(String msg, int ...v)
//	{
//		System.out.print(msg + v.length + "Contents");
//		
//		for(int x : v)
//			System.out.print(x + " ");
//		System.out.println();
//	}
//	
//	public static void main(String[] args) 
//	{
//		vaTest("One varargs",10);
//		vaTest("Three varargs",1,2,3);
//		vaTest("No varargs: ");
//	}
//}


/*--------------------------------------------------------------------------------------------------------------------------------------------------------------
		Overloading Vararg Methods
	
	Değişken uzunlukta bağımsız değişken alan bir yöntemi aşırı yükleyebilirsiniz. 
	Örneğin, aşağıdaki program vaTest( ) öğesini üç kez aşırı yükler:	
--------------------------------------------------------------------------------------------------------------------------------------------------------------*/

//class VarArgs3
//{
//	static void vaTest(int ... v)
//	{
//		System.out.print("vaTest(int ...):" + "Number of args: " + v.length +"Contents: ");
//		
//		for(int x : v)
//			System.out.print(x + " ");
//		System.out.println();
//	}
//	
//	static void vaTest(boolean ...v)
//	{
//		System.out.print("vaTest(boolean ...) " +
//				 "Number of args: " + v.length +
//				 " Contents: ");
//		for(boolean x : v)
//			System.out.print(x + " ");
//		System.out.println();
//	}
//}

/*--------------------------------------------------------------------------------------------------------------------------------------------------------------
	Bu program, bir varargs yönteminin aşırı yüklenebileceği her iki yolu da göstermektedir. 
	İlk olarak, vararg parametresinin türleri farklı olabilir. 
	Bu, vaTest (int ...) ve vaTest (boolean ...) için geçerlidir.
	
	Unutmayın, ... parametrenin belirtilen türde bir dizi olarak ele alınmasına neden olur.
	
	Bu nedenle, farklı türde dizi parametreleri kullanarak yöntemleri aşırı yükleyebildiğiniz gibi, farklı vararg türlerini kullanarak vararg yöntemlerini de aşırı yükleyebilirsiniz. 
	Bu durumda Java, hangi aşırı yüklenmiş yöntemin çağrılacağını belirlemek için tür farkını kullanır.
	
	Bir varargs yöntemini aşırı yüklemenin ikinci yolu, bir veya daha fazla normal parametre eklemektir. 
	VaTest (String, int ...) ile yapılan şey budur. 
	Bu durumda Java, hangi yöntemin çağrılacağını belirlemek için hem bağımsız değişken sayısını hem de bağımsız değişkenlerin türünü kullanır.
	
	Bir varargs yöntemi, varargs olmayan bir yöntemle de aşırı yüklenebilir. 
	Örneğin, vaTest(int x), yukarıdaki programda geçerli bir vaTest( ) aşırı yüklemesidir. 
	Bu sürüm yalnızca bir int bağımsız değişkeni olduğunda çağrılır. 
	İki veya daha fazla int bağımsız değişkeni geçirildiğinde, varargs sürümü vaTest (int... v) kullanılır.
--------------------------------------------------------------------------------------------------------------------------------------------------------------*/


/*--------------------------------------------------------------------------------------------------------------------------------------------------------------
		Varargs and Ambiguity
	
	Değişken uzunlukta bağımsız değişken alan bir yöntem aşırı yüklenirken beklenmeyen hatalar oluşabilir.
		
	Bu hatalar belirsizlik içerir, çünkü aşırı yüklenmiş bir varargs yöntemine belirsiz bir çağrı oluşturmak mümkündür. 
	Örneğin, aşağıdaki programı göz önünde bulundurun:
--------------------------------------------------------------------------------------------------------------------------------------------------------------*/

//class VarArgs4 {
//	 static void vaTest(int ... v) 
//	 {
//	 System.out.print("vaTest(int ...): " +
//	 "Number of args: " + v.length +
//	 " Contents: ");
//	 
//	 for(int x : v)
//		 System.out.print(x + " ");
//	 
//	 System.out.println();
//	 }
//	 
//	 static void vaTest(boolean ... v) 
//	 {
//	 System.out.print("vaTest(boolean ...) " +
//	 "Number of args: " + v.length +
//	 " Contents: ");
//	 
//	 for(boolean x : v)
//		 System.out.print(x + " ");
//	 
//	 System.out.println();
//	 }
//	 
//	 public static void main(String[] args)
//	 {
//	 vaTest(1, 2, 3); // OK
//	 vaTest(true, false, false); // OK
//	 vaTest(); //Error:Ambiguous
//	 }
//}

/*--------------------------------------------------------------------------------------------------------------------------------------------------------------
	Bu programda, vaTest( ) 'nin aşırı yüklenmesi tamamen doğrudur. 
	Ancak, bu program aşağıdaki çağrı nedeniyle derlenmez:
		vaTest(); // Error: Ambiguous!
	
	Vararg parametresi boş olabileceğinden, bu çağrı vaTest(int ...) veya vaTest(boolean...) çağrısına çevrilebilir. 
	Her ikisi de eşit derecede geçerlidir. 
	Bu nedenle, çağrı doğası gereği belirsizdir.
	
	
--------------------------------------------------------------------------------------------------------------------------------------------------------------*/


/*--------------------------------------------------------------------------------------------------------------------------------------------------------------
			Local Variable Type Inference with Reference Types
	
	Bölüm 3'te gördüğünüz gibi, JDK 10 ile başlayarak, Java yerel değişken türü çıkarımını destekler. 
	Yerel değişken türü çıkarımını kullanırken, değişkenin türünün var olarak belirtildiğini ve değişkenin başlatılması gerektiğini unutmayın.		
	
	Daha önceki örneklerde ilkel türlerle tür çıkarımı gösterilmiştir, ancak referans türlerle de kullanılabilir. 
	Aslında, referans türleri ile tür çıkarımı birincil kullanımı oluşturur. 
	İşte myStr adlı bir String değişkenini bildiren basit bir örnek:
		var myStr = "This is a string";
	
	Tırnak içine alınmış bir dize başlatıcı olarak kullanıldığından, String türü çıkarılır.
	Bölüm 3'te açıklandığı gibi, yerel değişken türü çıkarımının faydalarından biri, kodu düzene sokma yeteneğidir ve bu tür bir düzene sokmanın en belirgin olduğu referans türleridir.
	
	Bunun nedeni, Java'daki birçok sınıf türünün oldukça uzun adlara sahip olmasıdır. 
	Örneğin, Bölüm 13'te, giriş işlemleri için bir dosya açmak üzere kullanılan FileInputStream sınıfı hakkında bilgi edineceksiniz. 
	Geçmişte, burada gösterilene benzer geleneksel bir bildirim kullanarak bir FileInputStream bildirir ve başlatırdınız:
	
	FileInputStream fin = new FileInputStream("test.txt");
	
	With the use of var, it can now be written like this:
	
	var fin = new FileInputStream("test.txt");
	
	Burada, fin'in FileInputStream türünde olduğu sonucuna varılır, çünkü başlatıcısının türü budur. 
	Tür adını açıkça tekrarlamaya gerek yoktur.
	
	Bu fayda, jenerikleri içerenler gibi daha karmaşık bildirimlerde daha da belirginleşir. 
	Genel olarak, yerel değişken türü çıkarımının kolaylaştırıcı özniteliği, programınıza uzun tür adları girme sıkıntısını azaltmaya yardımcı olur.
	
	Elbette, yerel değişken türü çıkarımının kolaylaştırıcı yönü, programınızın okunabilirliğini azaltmaktan ve dolayısıyla anlamını gizlemekten kaçınmak için dikkatli kullanılmalıdır.
	Örneğin, burada gösterilene benzer bir bildirim düşünün:
		var x = o.getNext();
	
	Bu durumda, kodunuzu okuyan biri için x türünün ne olduğu hemen anlaşılmayabilir. Temel olarak, yerel değişken türü çıkarımı, akıllıca kullanmanız gereken bir özelliktir.
	
	Beklediğiniz gibi, aşağıdaki programın gösterdiği gibi, kullanıcı tanımlı sınıflarla yerel değişken türü çıkarımını da kullanabilirsiniz. 
	MyClass adlı bir sınıf oluşturur ve ardından bu sınıfın bir nesnesini bildirmek ve başlatmak için yerel değişken türü çıkarımını kullanır.
--------------------------------------------------------------------------------------------------------------------------------------------------------------*/

//class MyClass
//{
//	private int i;
//	
//	MyClass(int k) {i = k;} 
//	
//	public int getI() {
//		return i;
//	}
//	
//	public void setI(int k) {
//		if(k >= 0)
//			i = k;
//	}
//}
//
//class RefDemo
//{
//	public static void main(String[] args) 
//	{
//		var mc = new MyClass(10);
//		
//		System.out.println("Value of i in mc is "+ mc.getI());
//		mc.setI(19);
//		System.out.println("Value of i in mc is now" + mc.getI());
//		
//	}
//}

/*--------------------------------------------------------------------------------------------------------------------------------------------------------------
	In the program, pay special attention to this line var mc = new MyClass(10); // Notice the use of var here.
	
	Burada, mc türü MyClass olarak çıkarılacaktır, çünkü bu, yeni bir MyClass nesnesi olan başlatıcının türüdür.
	var ı dikkatli kullanmak gerekir çünkü okunabilirliği düşürme durumu olabilir.
--------------------------------------------------------------------------------------------------------------------------------------------------------------*/
