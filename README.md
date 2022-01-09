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
 
 La sottoclasse "DatiHospital" è molto simile alla sottoclasse "DatiUSA" sopra descritta, ma con la differenza che i dati raccolti in questa sono solo quelli necessari per
 effettuare il calcolo nel metodo "addColour()" per la determinazione del colore del giorno/settimana/mese dato in input

 
 ### Il service
 ### Lo stats
 
 ### Il Control
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
 
 ## Test
 
 ## Divisione dei compiti
- Naja Omar: alcuni metodi del connection, collegamento a postman e prima rotta del controller, gestione eccezioni, readme
- Sardellini Enrico Maria: model, rotte, gestione oggetti JSon, JavaDoc, Statistiche
