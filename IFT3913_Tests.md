# Documentation des Tests Implémentés

**Tâche 2 - Couverture : 24.58%**

## Tests Graphiques (Exécution Non-Automatique)

### 1. **CollapsiblePanelTest**
- **Chemin d'accès** : `src/test/java/com/marginallyclever/makelangelo/CollapsiblePanelTest.java`

- **testToggleVisibility()** :  
Ce test vérifie le comportement d'un panneau rétractable. Initialement, le panneau doit être rétracté, puis se développer pour afficher ses éléments à chaque clic. Il doit également se rétracter et masquer ses éléments lors d'un nouveau clic. Un *setup* préalable est nécessaire, ainsi que la définition d'une fonction pour vérifier la visibilité du panneau.  
**Note** : Le test ne s'exécute pas en CI car il s'agit d'un test graphique.

---

### 2. **DialogAboutTest**
- **Chemin d'accès** : `src/test/java/com/marginallyclever/makelangelo/DialogAboutTest.java`

- **testDialogComponents()** :  
Ce test vérifie la présence des composants dans la boîte de dialogue "DialogAbout". Il s'assure de la présence de la boîte elle-même, d'un élément avec le label `DialogAbout.AboutHTML`, et d'un bouton "OK" qui ferme la boîte de dialogue lorsqu'il est cliqué.  
**Note** : Ce test graphique n'est pas exécuté en CI.

---

### 3. **DialogBadFirmwareVersionTest**
- **Chemin d'accès** : `src/test/java/com/marginallyclever/makelangelo/DialogBadFirmwareVersionTest.java`

- **testDialogComponents()** :  
Ce test s'assure de la présence des composants dans la boîte de dialogue "DialogBadFirmwareVersion". Il vérifie l'existence de la boîte, d'un élément avec le label `DialogBadFirmwareVersion.Message`, et du bouton "OK" qui ferme la boîte lorsqu'il est cliqué.  
**Note** : Ce test graphique n'est pas exécuté en CI.

---

### Justification pour les Tests Graphiques
Même si cela dépasse légèrement les exigences initiales, il est crucial de tester les éléments graphiques de Makelangelo, étant donné la nature de l'outil. Ces tests garantissent que l'expérience utilisateur visuelle fonctionne correctement.

---

## Tests des Classes `Select`

### 1. **SelectBooleanTest**
- **Chemin d'accès** : `src/test/java/com/marginallyclever/makelangelo/select/SelectBooleanTest.java`

- **testInitialSelection()** :  
Ce test vérifie que le paramètre `arg0` détermine correctement l'état initial de l'objet `SelectBoolean`, en s'assurant que la méthode `isSelected()` fonctionne comme attendu.

- **testSetSelected()** :  
Test du bon fonctionnement du setter `setSelected()`.

- **testEventFiring()** :  
Ce test valide le déclenchement d'événements lorsque l'état de `SelectBoolean` change. Un *listener* est défini pour capturer ces changements. Lorsque `setSelected(true)` est appelé, l'événement doit être déclenché, et les anciennes et nouvelles valeurs doivent être correctes.

---

### 2. **SelectIntegerTest**
- **Chemin d'accès** : `src/test/java/com/marginallyclever/makelangelo/select/SelectIntegerTest.java`

- **testInitialValue()** :  
Test de l'initialisation de l'objet `SelectInteger`, en vérifiant que le paramètre `defaultValue` est bien géré, qu'il soit égal à 0 ou à un autre entier positif.

- **testSetValue()** :  
Test du bon fonctionnement du setter `setValue()`.

- **testEventFiring()** :  
Ce test vérifie le bon fonctionnement des événements lorsque la valeur de `SelectInteger` change. En modifiant l'état via `setValue(15)`, l'événement doit être capturé et les anciennes et nouvelles valeurs doivent être correctes.

---

### 3. **SelectPasswordTest**
- **Chemin d'accès** : `src/test/java/com/marginallyclever/makelangelo/select/SelectPasswordTest.java`

- **testInitialPassword()** :  
Test de l'initialisation de l'objet `SelectPassword`, en s'assurant que la valeur du mot de passe initial est correcte avec `getPassword()`.

- **testSetPassword()** :  
Test du setter `setPassword()` avec deux mots de passe différents pour garantir que l'appel multiple ne pose pas de problème.

---

### Justification des Tests `Select`
Les classes `Select` sont essentielles pour assurer une bonne expérience utilisateur, notamment pour la sélection des paramètres dans l'interface. Il est primordial de garantir leur bon fonctionnement pour que les options de configuration de l'outil fonctionnent sans problème.

---

## Test de `PaperSize`

### 1. **PaperSizeTest**
- **Chemin d'accès** : `src/test/java/com/marginallyclever/makelangelo/paper/PaperSizeTest.java`

- **testConstructorAndGetter()** :  
Ce test vérifie que l'objet `PaperSize` est bien initialisé avec les trois attributs attendus.

- **testToString()** :  
Test de la méthode `toString()` pour s'assurer que la représentation textuelle de l'objet est correcte et lisible.

---

### Justification pour `PaperSizeTest`
Ces tests garantissent que la classe `PaperSize` fonctionne correctement en vérifiant l'initialisation des objets et leur représentation textuelle. Cela est crucial pour éviter des erreurs dans la manipulation des dimensions et pour assurer une bonne lisibilité lors du débogage ou dans l'interface utilisateur.
