# Tâche 3

## Changements apportés

Pour rendre la GitHub Action compatible avec plusieurs flags JVM, nous avons utilisé `matrix.jvm_flags` dans le fichier de configuration. Cela permet d'exécuter le pipeline Maven avec une série de paramètres différents pour observer leurs effets. Voici un résumé des changements principaux :

1. **Ajout de `matrix.jvm_flags`** : Une matrice de cinq flags JVM a été intégrée pour permettre des tests en parallèle avec différentes options.
2. **Modification de `MAVEN_OPTS`** : Chaque configuration de flag est assignée à la variable d'environnement `MAVEN_OPTS`, permettant à Maven d'utiliser la configuration JVM définie pour chaque itération.
3. **Nouvelle étape de logging** : Un log spécifique a été ajouté pour afficher le flag JVM utilisé ainsi que la couverture de code obtenue.

## Choix des flags JVM et justification

Les flags sélectionnés visent à améliorer les performances et la visibilité du comportement de la JVM pendant l'exécution du code. Voici une explication des raisons de leur sélection :

1. **`-XX:+UseG1GC`**  
   - **Motivation** : Active le garbage collector G1, qui est optimisé pour les applications interactives avec des pauses courtes et des performances plus constantes. Cela améliore la gestion de la mémoire, réduisant les pauses dues au garbage collection et contribuant à la stabilité des tests.
   - **Impact possible** : Amélioration de la performance et de la stabilité en limitant les interruptions dues au garbage collection.

2. **`-XX:+UnlockDiagnosticVMOptions`**  
   - **Motivation** : Ce flag active des options de diagnostic supplémentaires dans la JVM, qui peuvent être cruciales pour analyser les problèmes de performance ou de stabilité durant les tests.
   - **Impact possible** : Amélioration de l’observabilité, car il permet d’activer des flags de diagnostic additionnels pour des analyses plus détaillées.

3. **`-XX:+PrintGCDetails`**  
   - **Motivation** : Fournit des informations détaillées sur les opérations de garbage collection dans la console. Ce flag aide à comprendre la fréquence et la durée des cycles de garbage collection, utiles pour optimiser la mémoire.
   - **Impact possible** : Amélioration de la visibilité et de l’observabilité, surtout pour diagnostiquer des problèmes de mémoire.

4. **`-XX:+PrintCompilation`**  
   - **Motivation** : Affiche chaque méthode compilée par la JVM en temps réel. Cela est utile pour analyser le coût en temps de la compilation et les éventuels ralentissements dus aux compilations JIT.
   - **Impact possible** : Amélioration de l’observabilité, en permettant de détecter des problèmes de performance potentiels liés à la compilation dynamique.

5. **`-XX:+HeapDumpOnOutOfMemoryError`**  
   - **Motivation** : Crée un dump de la mémoire en cas d'erreur `OutOfMemoryError`, facilitant le diagnostic et la résolution de fuites de mémoire.
   - **Impact possible** : Amélioration de la qualité et de la détection d’erreurs graves, car le dump fournit un instantané de l’état de la mémoire pour l'analyse post-mortem.

## Humour

Concernant **`-XX:+UnlockDiagnosticVMOptions`** : Comme une porte secrète dans un jeu vidéo, cette option ouvre des fonctionnalités cachées... mais parfois, mieux vaut ne pas trop explorer; le boss qu'on affronterait pourrait nous prendre des jours à faire tomber (et beaucoup de potions de soin, ou en d'autre terme, beaucoup de café) ! Au moins prenez avec vous un soigneur (le stagiaire), vous pourrez alors vous attribuer la gloire du coup fatal alors qu'il supportait vos blessures (et détresses mentales) tout le long...
