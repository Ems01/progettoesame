 # PROGETTO NAJA - SARDELLINI

 # introduzione e descrizione del progetto
 
 # Il Model
 
 # Rotte e descrizione
 
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
 
 # Test
 
 # Divisione dei compiti
- Naja Omar: alcuni metodi del model, collegamentoa a postman e prima rotta del controller, getione eccezioni, readme
- Sardellini Enrico Maria: model, rotte, gestione oggetti JSon, JavaDoc
