# language: pt
Funcionalidade: Gerenciar registros na Web Tables do DemoQA
  Como usuário do DemoQA
  Quero acessar a seção de Web Tables
  Para criar, editar e deletar registros

  Cenário: Criar um registro
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

