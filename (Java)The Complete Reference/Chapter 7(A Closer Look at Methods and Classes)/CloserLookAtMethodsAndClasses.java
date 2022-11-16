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
	Ancak, bu maçın her zaman kesin olması gerekmez. 
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
	Bu nedenle, test(int) bulunamadıktan sonra, Java i'yi doublea yükseltir ve ardından test(double)'ı çağırır. 
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
 	Box'ın en son sürümü aşağıdadır:		
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
 	
 	Neyse ki, bu sorunların çözümü oldukça kolaydır: Kutu oluşturucuyu aşırı yükleyin, böylece az önce açıklanan durumları ele alır. 
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
	
	Genel olarak, bir bilgisayar dilinin bir alt rutine bir argüman geçirmesinin iki yolu vardır. İlk yol, call-by-value.
	Bu yaklaşım, bir argümanın değerini alt rutinin resmi parametresine kopyalar. 
	Bu nedenle, alt rutinin parametresinde yapılan değişikliklerin argüman üzerinde hiçbir etkisi yoktur.
	
	Bir argümanın aktarılabilmesinin ikinci yolu call-by-reference
	Bu yaklaşımda, bir argümana referans (argümanın değeri değil) parametreye aktarılır.
	Alt rutin içinde, bu referans çağrıda belirtilen gerçek argümana erişmek için kullanılır. 
	Bu, parametrede yapılan değişikliklerin, alt rutin çağırmak için kullanılan argümanı etkileyeceği anlamına gelir. 
	Gördüğünüz gibi, Java tüm argümanları geçmek için call by value kullansa da, kesin etki ilkel bir tip veya referans türü geçilmiş olup olmadığı arasında farklılık gösterir.
	
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
  	Bu, etkili bir şekilde, nesnelerin başvuru yoluyla çağrı kullanılarak yöntemlere geçirilmiş gibi davrandığı anlamına gelir. 
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


