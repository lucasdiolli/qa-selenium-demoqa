# language: pt
Funcionalidade: Gerenciar registrosdo DemoQA
  Como usuário do DemoQA
  Quero acessar funcionalidades
  Para criar, editar e deletar registros

  Cenário: Criar um registro no Practice Form
    Dado que acesso o site DemoQA
    Quando escolho a opcao Forms na pagina inicial
    E clico no submenu Practice Form
    E crio um novo registro
    Então valido que foi exbido popup com dados do registro
    E fecho o popup


  Cenário: Abrir nova janela e validar mensagem
  Dado que acesso o site DemoQA
  Quando escolho a opcao Alerts, Frame & Windows na pagina inicial
  E clico no submenu Browser Windows
  E clico no botão New Window
  Entao uma nova janela deve ser aberta com a mensagem "This is a sample page"
  E fecho a nova janela aberta

  Cenário: Gerenciar múltiplos registros dinamicamente
    Dado que acesso o site DemoQA
    Quando escolho a opcao Elements na pagina inicial
    E clico no submenu Web Tables
    E crio 12 novos registros dinâmicos
    E edito o último registro criado para o nome "Editado"
    E deleto todos os registros criados
    Então valido que a tabela está vazia ou sem os registros criados

  Cenário: Interagir com a Progress Bar
    Dado que acesso o site DemoQA
    Quando escolho a opcao Widgets na pagina inicial
    E clico no submenu Progress Bar
    E inicio a barra e paro antes dos 25%
    Então valido que o valor da barra é menor ou igual a 25%
    E inicio a barra novamente e reseto ao chegar em 100%

  Cenário: Ordenar lista de forma crescente
    Dado que acesso o site DemoQA
    Quando escolho a opcao Interactions na pagina inicial
    E clico no submenu Sortable
    E ordeno os elementos da lista na ordem crescente
    Então valido que os elementos estão na ordem correta

  Cenário: Ordenar lista de forma decrescente
    Dado que acesso o site DemoQA
    Quando escolho a opcao Interactions na pagina inicial
    E clico no submenu Sortable
    E ordeno os elementos da lista na ordem decrescente
    Então valido que os elementos estão na ordem "Six" para "One"
