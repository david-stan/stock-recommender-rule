# SBNZ predlog projekta – Stock Exchange Recommender

Student: David Stanojević

Indeks: SW-41/2016

Motivacija

Konzistentan profit od ulaganja u akcije je oduvek bio izazovan problem, naročito zbog kompleksne prirode kretanja cijena na berzi. Većina ulagača amatera nemaju duboko razumevanje prirode kretanja cijena akcija, odnosno šablona ponašanja tržišta i stoga ne uspevaju da efikasno ulože novac i profitiraju od trgovine.

Pregled problema

Problem se sastoji iz predloga akcija u koje bi korisnik trebao da uloži svoj novac. Količina uloženog novca varira u odnosu trenutnog stanja novca sa kojim korisnik raspolaže. Pri odabiru akcija uzima se u obzir trenutni trend kretanja cijena za određenu akciju kao i njezin istorijski trend, odnosno procena koliko je ulaganje rizično. Korisnik bi trebao biti u mogućnosti da odabere oblast koja ga interesuje (industrija, softver, nekretnine,..). Pojedini delovi problema objašnjeni su u sledećoj literaturi:

- [https://journals.sagepub.com/doi/full/10.1177/2158244015579941](https://journals.sagepub.com/doi/full/10.1177/2158244015579941)
- [http://cs229.stanford.edu/proj2017/final-reports/5244939.pdf](http://cs229.stanford.edu/proj2017/final-reports/5244939.pdf)

Metodologija

Ulazi u sistem:

- Tekuće, dnevne vrednosti akcija, uključujući i istorijske podatke za svaku akciju. ([https://github.com/RomelTorres/alpha\_vantage](https://github.com/RomelTorres/alpha_vantage))
- Kvartalni izveštaji za svaku akciju ([https://financialmodelingprep.com/developer/docs/](https://financialmodelingprep.com/developer/docs/))

Izlazi:

- Spisak akcija u koje bi korisnik mogao da ulaže, kao i količina novca koju bi trebao da uplati.

Primer:

- Razmatramo akciju koja u zadnje vreme (par meseci) ima odličan trend, ali je u prošlosti imala velike padove i nekonzistentnost, stoga takvu akciju smatramo većim rizikom i dajemo joj manju ocenu. Dok na primer, za akciju koja se skoro pojavila na tržištu i nema neku veliku vrednost, ali je jako stabilna i ima konzistentan rast, ocenjujemo većom ocenom i predlažemo je korisniku.

Pravila:
- Prvi korak je profilisanje korisnika na osnovu njegovog odabira sektora na osnovu ličnih preferencija kao i trenutnog statističkog stanja iz konkretnih oblasti (sektora). Da bi se odabrao odgovarajući sektor koriste se pravila na osnovu backward chaining principa koja biraju konačnu oblast. Primer pravila:


<pre><code>rule "Health Sector rule"
	salience ((10*($vol*0.00001 + $close*1.2))/(10*($std+0.1)*(($count+0.1)*0.1)))
	when 
		$sec: Sectors(sector == Sector.HEALTH_CARE, $std: std, $vol: volume, $close: close, $attr: attributes)
		$sto: Stocks(sector == Sector.NA, $profile: chosenProfile)
		Number($count: intValue >= 0) from accumulate(
			Profiles($p: this, this memberOf $attr) from $profile,
			count($p)
		)
	then
		System.out.println("Health: " + 10*($vol*0.00001 + $close*1.2)/(10*($std+0.1)*(($count+0.1)*0.1)));
		modify($sto) { setSector(Sector.HEALTH_CARE) }
end
</code></pre>

- Drugi korak je filtriranje akcija iz odabrane oblasti, koje su dobijene iz eksternog servisa, kao i na osnovu vrednosti njihovog rizika. Na osnovu forward chaining-a se posmatraju parametri rizika i ackije svrstavaju u odredjene klase rizika. Primer ulančavanja:

<pre><code>
rule "High risk"
    when
        $risk: Risk(riskGroup == RiskGroup.NA, score <= 10)
    then
    	System.out.println("High risk");
    	modify($risk) { setRiskGroup(RiskGroup.HIGH_RISK) }
end
</code></pre>
<pre><code>
rule "Thresh low"
	when
		$risk: Risk(riskGroup == RiskGroup.LOW_RISK, threshold == 0, $attr: riskAssessments)
		Number($count: intValue >= 0) from accumulate(
			Profiles($p: this) from $attr,
			count($p)
		)
	then
		modify($risk) { setThreshold(7*(1+0.1*$count)) }
end
</code></pre>
<pre><code>
rule "Process stocks"
	when
		$risk: Risk(riskGroup != RiskGroup.NA, threshold != 0)
	then
		$risk.processStocks();
end
</code></pre>

- Treći korak je upit koji dobavlja Risk fact i vraća filtriranu listu akcija nazad klijentu u json formatu.
