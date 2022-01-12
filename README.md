 # PROGETTO NAJA - SARDELLINI
 ## introduzione e descrizione del progetto
 
 Questa applicazione è stata realizzata per un compito d'esame del corso di Programmazione ad Oggetti dell'UNIVPM nell'anno 2021/22. Autori del progetto sono due studenti del
 corso di ingegneria informatica.
 Scopo dell'applicazione è di restituite i dati relativi ad un dato giorno dell'andamento della situazione COVID-19 negli Stati Uniti, facendo un'analisi sul territorio
 nazionale. Per limiti dovuti all'API di riferimento, tali dati sono disponibili solo nel periodo dal 13 gennaio 2020 al 7 marzo 2021.
 A livello di programmazione, questo programma consiste in diversi sottoprogrammi raggruppati in rispettivi packages:
 
 - **project**: contiene il main del programma e il file JSON da cui prendere i dati
 - **project.control**: contiene l'implementazione e la gestione delle rotte
 - **project.model**: contiene due classe da cui si instanziano gli oggetti a mano a mano che viene effettuato il parsing,  , delle varie date dal file JSon
 - **project.service**: vengono impletementati e gestiti, con delle opportune eccezioni, i metodi poi richiamati dal model
 - **project.stats**: vengono sviluppati alcuni metodi per l'analisi di alcuni dati statistici
 
 Contiene, inoltre, un package a parte su cui vengono svolti dei test sul corretto funzionamento del programma
 
 ### Il Main
 
 ![Main](https://user-images.githubusercontent.com/95374284/148680326-06585bd9-d2c5-4d1a-b31e-7b9c351ea251.JPG)
 
 Il codice qui presente è molto breve, ma fondamentale: viene specificato che l'applicazione che si sta sviluppando è di tipo SpringBoot. Ciò significa che, a differenza di un
 classico programma sviluppato in Java, un programma di tipo SpringBoot fornisce funzionalità aggiuntive al framework di partenza, in questo caso "Covid Tracking".
 Il file USA.json contiene tutti i dati covid dei giorni compresi nel periodo sopra definito. Essi si sono ottenuti mettendo l'indirizzo dell'API su postman, da cui poi si è
 generato il file Json fondamentale per effettuare il parsing dei dati, di cui si parlerà in seguito.
 
 ![Postman1](https://user-images.githubusercontent.com/95374284/148680920-ad5c1ba3-f195-4c4d-a8d3-d964ce550aaa.JPG)
 
 ### Il Model
 
 ![Model1](https://user-images.githubusercontent.com/95374284/148681573-c40aced4-21fc-4d96-9953-4d16d10c8ab2.JPG)
 
 La prima classe presente è una interfaccia, dunque costituita da soli metodi vuoti. Tali metodi verrano poi eseguiti dalle classi che implementano "Dati" tramite il
 meccanismo di overriding
 
 ![Model2](https://user-images.githubusercontent.com/95374284/148681779-04151db8-d0ac-40f5-9763-8d245705eb2f.JPG)
 
 Questa è la sottoclasse in cui vengono raccolte le informazioni dei singoli giorni, poi gestite con i dovuti metodi get() e set(). Il primo per la possibilità di ottenere da
 altre classi questi dati in sola lettura, il set per definire quei determinati valori trovati a quel giorno. Tutti questi dati verrano in seguito salvati in un vettore dinamico
 definito nel service
 
 ![Model3](https://user-images.githubusercontent.com/95374284/148682047-169a5d62-6339-472f-b263-5737e3f0d574.JPG)
 
 La sottoclasse "DatiHospital" è molto simile alla sottoclasse "DatiUSA" sopra descritta, ma con la differenza che i dati raccolti in
 questa sono solo quelli necessari per
 effettuare il calcolo nel metodo "addColour()" per la determinazione del colore del giorno/settimana/mese dato in input

 
 ### Il Service
 
 ![Service1](https://user-images.githubusercontent.com/95374284/149141922-733d1ddb-6f83-439a-b22f-8c0d3eaa4fd5.JPG)


 La prima classe è una semplice interfaccia, e ha il solo scopo di definire i vari metodi che verranno poi implementati e nella classe "connection".
 
 ![Eccezione](https://user-images.githubusercontent.com/95374284/149142345-d1d7027c-cf23-49bd-a1ab-7647b5472035.JPG)

 Si apre una breve parentesi per definire la classe "EccezionePersonalizzata", inserita in un altro package ma definita per l'uso delle eccezioni da lanciare proprio nella
 classe "connection", centrale nello sviluppo dell'intero programma.
 
 ![Service2](https://user-images.githubusercontent.com/95374284/149142202-79243211-c231-4c76-8f3f-b0e43d1783a4.JPG)
 ![Service3](https://user-images.githubusercontent.com/95374284/149155284-5a2730a1-d48d-4329-a2e6-ffc31c83a549.JPG)
 ![Service4](https://user-images.githubusercontent.com/95374284/149155502-0e619338-909b-489f-a7c7-50048930345d.JPG)

 
 Il primo metodo presente nella classe "connection" è chiamato parsingData(), e ha lo scopo di definire due vettori dinamici vett1 e vett2 dove vengono a mano a mano
 immagazzinati i vari oggetti di tipo rispettivamente DatiUSA e DatiHospital, con le proprietà definite in precedenza nel Model e prese dal file JSon.
 
 I successivi metodi sono quelli che poi verranno usati dalle rotte, per tutti e 5 si implementa il dovuto metodo per la ricerca del giorno/settimana/mese/2 giorni dato/i in
 input per poi svolgere il compito richiesto
 
 ![Service5](https://user-images.githubusercontent.com/95374284/149155617-62b45f0f-3aad-4996-b32d-5844a7305721.JPG)

 
 Il metodo getToday() chiede in input un giorno di cui si vuole stampare il bollettino COVID, successivamente cerca all'interno del vettore di tipo DatiUSA se quella data
 fornita in input è presente. In caso affermativo, stampa in output le informazioni richieste. In caso negativo, viene lanciata l'eccezione con il messaggio "Day not
 found!"
 
 ![Service6](https://user-images.githubusercontent.com/95374284/149155959-530bab4a-3c9d-43e0-b6c0-cb43c7978dcb.JPG)

 
 getWeek() funziona in maniera del tutto analoga: chiede in input un giorno e vengono restituite 2 diverse informazioni: la prima riguarda i bollettini dei 7 giorni
 successivi al giorno dato in input, la seconda stima l'incremento/decremento di alcuni dati nel corso della settimana. In caso di settimana "inesistente" (basta che su 7 giorni
 uno solo non sia all'interno del range di date precedentemente definito) viene lanciata l'eccezione
 
 ![Service7](https://user-images.githubusercontent.com/95374284/149156189-b9fcb19a-fdd7-4bea-b358-f30880e1b313.JPG)
 ![Service8](https://user-images.githubusercontent.com/95374284/149156450-70a70bc2-4739-40b3-8a2b-78350093c477.JPG)
 
 Il metodo getMonth() è leggermente più complesso perchè chiede in input due parametri: il mese e l'anno di interesse. Una volte prese queste informazioni, viene restituito
 in output il bollettino di tutti i giorno del mese richiesto.
 
 ![Service9](https://user-images.githubusercontent.com/95374284/149140693-62a51978-a077-46c6-8bb6-f443c6f3785a.JPG)
 
 getColour() chiede in input un colore e restituisce tutti i giorni che hanno una situazione pandemica tale dall'essere di quel colore (bianco, rosso etc.).
 Se il colore non esiste (ad esempio, la "zona viola"), viene lanciata l'eccezione.
 
 ![Service10](https://user-images.githubusercontent.com/95374284/149141044-7d636fab-010c-4668-808b-cb23f450b391.JPG)
 
 L'ultimo metodo implementato chiede in input due date diverse e fa il confronto della situazione COVID tra i due giorni. Basta che uno solo dei due non esista per lanciare
 l'eccezione.
 
 ### Lo Stats
 
 ### Il Control
 
 ![Control1](https://user-images.githubusercontent.com/95374284/149161993-e4686fcf-3c37-4215-bb4e-d77fd2c29f41.JPG)
 ![Control2](https://user-images.githubusercontent.com/95374284/149162008-bae7a93c-35d3-4af7-9a74-5c0b27edb30d.JPG)
 
 All'interno del Controller vengono gestiti i metodi che consentono l'utilizzo delle rotte da web service esterni (tra cui postman oppure internet)

 
 **Rotte e descrizione**
 
 <table class="table">
  <thead>
    <tr>
      <th scope="col">#</th>
      <th scope="col">Nome</th>
      <th scope="col">Tipo</th>
      <th scope="col">Descrizione</th>
    </tr>
  </thead>
  <tbody>
    <tr>
      <th scope="row">1</th>
      <td>/day</td>
      <td>GET</td>
      <td>Restituisce il bollettino COVID del giorno dato in input</td>
    </tr>
    <tr>
      <th scope="row">2</th>
      <td>/week</td>
      <td>GET</td>
      <td>Restituisce il bollettino COVID dei 7 giorni successivi a quello dato in input</td>
    </tr>
    <tr>
      <th scope="row">3</th>
      <td>/month</td>
      <td>GET</td>
      <td>Restituisce tutti i bollettini COVID del mese dato in input</td>
    </tr>
   <tr>
      <th scope="row">4</th>
      <td>/colour</td>
      <td>GET</td>
      <td>Restituisce tutti i bollettini COVID dei giorni che sono risultati di un certo colore (bianco, giallo etc.)</td>
    </tr>
   <tr>
      <th scope="row">5</th>
      <td>/2days</td>
      <td>GET</td>
      <td>Effettua un confronto dei dati COVID tra 2 giorni diversi, dati entrambi in input</td>
    </tr>
  </tbody>
</table>

Alcuni esempi di utilizzo delle rotte:

**/day**

![Postman2](https://user-images.githubusercontent.com/95374284/149158978-a8cce1f0-f12a-4620-8e8d-121b23231aea.JPG)

**/week**

 ![Postman3](https://user-images.githubusercontent.com/95374284/149159350-7f009f66-1a15-4086-9f19-25f3f6823732.JPG)

**/month**

![Postman4](https://user-images.githubusercontent.com/95374284/149160038-373fc347-60ae-4084-8d31-99e35c60ebf9.jpeg)

**/colour**

![Postman5](https://user-images.githubusercontent.com/95374284/149160354-aa85f13c-622e-4096-94da-643c2b25aea3.JPG)

**/2days**

![Postman6](https://user-images.githubusercontent.com/95374284/149160780-4d512069-4d5b-4365-9562-29dd964acc54.JPG)ù

**/get chiamato da Google Chrome**

![Chromegetday](https://user-images.githubusercontent.com/95374284/149162861-e2b3b7ef-7fad-4ef2-84de-c9895258b73c.JPG)


 ## Test
 
 ![Test](https://user-images.githubusercontent.com/95374284/149158564-df5b7e73-65bb-4808-93d8-6955cd3abd4e.JPG)

 
 Nel programma sono presenti alcuni test che controllano la corretta funzione di alcuni metodi, in particolare vengono controllati il corretto funzionamento (e non
 funzionamento) del metodo getDay() e il corretto funzionamento per get2Days().
 Appoggiandosi ad un oggetto di tipo connection, si richiamato tali metodi e, attraverso il JUnit test, si verifica se tali metodi effettuano ciò che ci si aspetta o meno.
 Infatti, dei 3 test implementati, 2 funzionano correttamente mentre il terzo (testDataNoOK()) restituisce errore, in quanto gli viene passato come argomento una data
 non appartenente al range dell'API
 
 ## Divisione dei compiti
- Naja Omar: alcuni metodi del connection, collegamento a postman e prima rotta del controller, gestione eccezioni, readme
- Sardellini Enrico Maria: model, rotte, gestione oggetti JSon, JavaDoc, Statistiche
