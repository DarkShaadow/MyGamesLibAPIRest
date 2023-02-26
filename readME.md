# Back-End projet Angular CHAIDRON, DULUYE, SCHAFFAUSER

## Lancement de l'API

- Afin de lancer l'API ainsi que la base de donnée, il suffit de lancer la config `Docker - App`. Cette dernière lancera la base de données, compilera l'API et la lancera dans une image Docker, le swagger sera disponible à cette URL : http://localhost:8082/my-games-lib-api-rest/swagger-ui
- La configuration a été faite sur IntelliJ 2023, il se peut que le script `Copy jar` plante au lancement sur les versions antérieures. Il suffit alors de lancer la config `Create jar` puis de déplacer le jar du dossier `/target` vers le dossier `/docker/app` et d'ensuite lancer l'API contenerisée
- Pour lancer l'API non dockerizée, il suffit de seléctionner le profil `UNDOCKERIZED` sur Maven et de lancer la classe Main