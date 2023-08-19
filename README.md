# Implementação de um simulador de um autômato finito (AFD and AFND)
[![NPM](https://img.shields.io/npm/l/react)](https://github.com/Gi0vanniR0sa/Finite_automato_simulator/blob/main/LICENSE) 

## Sobre o projeto

O presente repositório têm como objetivo apresentar o trabalho desenvolvido como requisito parcial para aprovação na disciplina de Teoria da Computação ministrada Prof. Me. [Welligton Della Mura](https://github.com/wellingtondellamura).  

A aplicação consiste em um simulador do comportamento esperado por um autômato finito que abrange as linguagens de tipo 3 (com exceção dos FND-ε). Seu funcionamento se baseia na disposição de dois arquivos de tipos distintos que fornecem a definição formal da máquina, o primeiro em formato .txt deve conter uma 4-upla (Σ, Q, q0, F) onde cada um destes elementos deve estar organizado de maneira que ocupe as quatro primeiras linhas do documento, e o segundo, do tipo .json viabiliza a função de transição (δ) ausênte na descrição anterior.

A partir do fornecimento de ambas as entradas o autômato é "carregado" e então solicita uma cadeia para realizar o processamento da mesma, permitindo ao final verificar sua aceitação ou rejeição. Ao final é gerado um segundo arquivo .txt localizado no mesmo caminho fornecido para a localização do primeiro, contendo as informações pertinentes a execução como: palavra de entrada, resultado do cumprimento e tempo de processamento em ms.

## Diagrama 
![Imagem 1](https://github.com/Gi0vanniR0sa/Finite_automato_simulator/blob/main/Assets/Diagrama_Funcionamento_AF.png) 

## Linguagem utilizada

  - Java

## Autores

  - Giovanni Rosa Marcomini
  - Giovanne Amaral
