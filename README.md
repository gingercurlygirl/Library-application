# Biblioteksapplikation med Java, JDBC och MySQL

## Hur programmet körs?
1. Kör MySQL script `library_database.sql`
2. Ställa in environment variabler `MYSQL_USER` och `MYSQL_PASSWORD` enligt din MySQL
3. Kör `src/Main.java`

## Applikations beskrivning

Biblioteksapplikationen består av ett Java-program, en SQL-databas och en JDBC-anslutning.

SQL kommer med böcker installerade.

Det börjar med huvudmenyn, där du väljer användare eller administratör.

### Användare
Om du väljer en användare måste du ange ditt användarnamn. Sedan öppnas en ny meny där du kan låna en bok, lämna
tillbaka den, se din lista över lånade böcker och söka efter en bok på två sätt: efter boktitel eller bokförfattare.
När du lånar en bok frågar den dig om författaren och titeln på boken (du behöver inte ange hela titeln eller
författaren, det räcker med ett ord eller bara för- eller efternamnet Om den hittar mer än en bok med samma författare
eller titel kommer den att erbjuda ett nummer framför varje bok, vilket gör det lättare för dig att bestämma vilken bok
du vill låna). Samma sak gäller förstås för att lämna tillbaka en bok, eftersom du kan ha lånat en serie böcker av samma
författare. När du lånar en bok har du ett lånedatum och 30 dagars nedräkning tills du ska lämna tillbaka den.

### Administratör
Administratören har en andra meny, som inkluderar att lägga till en ny bok, ta bort en befintlig och en lista över alla
böcker och deras tillgänglighet. När du lägger till böcker är det nödvändigt att skriva hela bokens titel och
författarens för- och efternamn för att lägga till rätt bok, och vid radering är det nödvändigt att endast ange bok-ID.
