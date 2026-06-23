## TB_USER
(Armazena os utilizadores registados na plataforma)

|**Coluna**|**Tipo**|**Restrição**|**Descrição**|
|---|---|---|---|
|`id`|VARCHAR(36)|Chave Primária (PK)|UUID identificador único do utilizador.|
|`email`|VARCHAR|UNIQUE, NOT NULL|Correio eletrónico do utilizador.|
|`hashedPassword`|VARCHAR|NOT NULL|Senha encriptada.|
|`name`|VARCHAR|NOT NULL|Nome do utilizador.|

## TB_CURRENCY
(Armazena todas as moedas suportadas, fiduciárias e cripto)

|**Coluna**|**Tipo**|**Restrição**|**Descrição**|
|---|---|---|---|
|`id`|VARCHAR(36)|Chave Primária (PK)|UUID identificador único da moeda.|
|`symbol`|VARCHAR(10)|UNIQUE, NOT NULL|Símbolo da moeda (ex: BRL, BTC).|
|`name`|VARCHAR|NOT NULL|Nome completo da moeda.|
|`isFiat`|BOOLEAN|NOT NULL|Indica se é moeda fiduciária (true) ou cripto (false).|

## TB_WALLET
(Armazena as carteiras dos utilizadores e a sua moeda base)

|**Coluna**|**Tipo**|**Restrição**|**Descrição**|
|---|---|---|---|
|`id`|VARCHAR(36)|Chave Primária (PK)|UUID identificador único da carteira.|
|`owner_id`|VARCHAR(36)|Chave Estrangeira (FK)|UUID do utilizador dono (`TB_USER`).|
|`baseCurrency_id`|VARCHAR(36)|Chave Estrangeira (FK)|UUID da moeda base da carteira (`TB_CURRENCY`).|

## TB_ASSET
(Armazena os saldos atuais de moedas específicas dentro de uma carteira)

|**Coluna**|**Tipo**|**Restrição**|**Descrição**|
|---|---|---|---|
|`id`|VARCHAR(36)|Chave Primária (PK)|UUID identificador único do ativo.|
|`wallet_id`|VARCHAR(36)|Chave Estrangeira (FK)|UUID da carteira associada (`TB_WALLET`).|
|`currency_id`|VARCHAR(36)|Chave Estrangeira (FK)|UUID da moeda do ativo (`TB_CURRENCY`).|
|`quantity`|DECIMAL(18,8)|NOT NULL|Quantidade atual do ativo.|

## TB_CURRENCY_QUOTE
(Armazena o histórico de taxas de câmbio entre as moedas)

|**Coluna**|**Tipo**|**Restrição**|**Descrição**|
|---|---|---|---|
|`id`|VARCHAR(36)|Chave Primária (PK)|UUID identificador único da cotação.|
|`currency_id`|VARCHAR(36)|Chave Estrangeira (FK)|UUID da moeda a ser cotada (`TB_CURRENCY`).|
|`baseCurrency_id`|VARCHAR(36)|Chave Estrangeira (FK)|UUID da moeda de referência (`TB_CURRENCY`).|
|`price`|DECIMAL(18,8)|NOT NULL|Valor da conversão.|
|`timestamp`|DATETIME|NOT NULL|Data e hora exatas da cotação.|

## TB_TRANSACTION
(Armazena o histórico de depósitos, saques, compras e vendas)

|**Coluna**|**Tipo**|**Restrição**|**Descrição**|
|---|---|---|---|
|`id`|VARCHAR(36)|Chave Primária (PK)|UUID identificador único da transação.|
|`wallet_id`|VARCHAR(36)|Chave Estrangeira (FK)|UUID da carteira que realizou a transação (`TB_WALLET`).|
|`currency_id`|VARCHAR(36)|Chave Estrangeira (FK)|UUID da moeda transacionada (`TB_CURRENCY`).|
|`quantity`|DECIMAL(18,8)|NOT NULL|Quantidade movimentada.|
|`priceAtTime`|DECIMAL(18,8)|NOT NULL|Preço da moeda no momento da transação.|
|`type`|VARCHAR(20)|NOT NULL|Tipo de transação (BUY, SELL, DEPOSIT, WITHDRAW).|
|`executedAt`|DATETIME|NOT NULL|Data e hora da execução.|
