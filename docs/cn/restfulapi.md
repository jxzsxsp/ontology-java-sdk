# Ontology Restful API

* [Introduction](#Introduction)
* [Restful API list](#Restful API list)
* [Errorcode](#Errorcode)

Restful API list

| Method | Memo | url |
| :---| :---| :---|
| get_gen_blk_time | 获取出块时间 | GET /api/v1/node/generateblocktime |
| get_conn_count | 获取连接数 | GET /api/v1/node/connectioncount |
| get_blk_txs_by_height | 通过区块高度获取区块交易信息 | GET /api/v1/block/transactions/height/:height |
| get_blk_by_height | 通过区块高度获取区块详情 | GET /api/v1/block/details/height/:height |
| get_blk_by_hash | 通过HASH获取区块详情 | GET /api/v1/block/details/hash/:hash |
| get_blk_height | 获取区块高度 | GET /api/v1/block/height |
| get_blk_hash | 通过区块高度获取HASH | GET /api/v1/block/hash/:height |
| get_tx | 通过HASH获取交易信息 | GET /api/v1/transaction/:hash |
| get_storage |  | GET /api/v1/storage/:hash/:key|
| get_balance | 获取地址下的余额 | GET /api/v1/balance/:addr |
| get_contract_state | 通过HASH获取合约状态 | GET /api/v1/contract/:hash |
| get_smtcode_evt_txs |  | GET /api/v1/smartcode/event/transactions/:height |
| get_smtcode_evts |  | GET /api/v1/smartcode/event/txhash/:hash |
| get_blk_hgt_by_txhash |  | GET /api/v1/block/height/txhash/:hash |
| get_merkle_proof |  | GET /api/v1/merkleproof/:hash|
| post_raw_tx |  | post /api/v1/transaction |


## Introduction

本文档描述了用于Onchain本体的http/https的restful api格式。

## Restful API list

### Response parameters descri

| Field | Type | Description |
| :--- | :--- | :--- |
| Action | string | action name |
| Desc | string | description |
| Error | int64 | error code |
| Result | object | execute result |
| Version | string | version information |

### 1. get_gen_blk_time

获取出块时间

##### GET

```
/api/v1/node/generateblocktime
```
#### Request Example:

```
curl -i http://server:port/api/v1/node/generateblocktime
curl -i http://es.xiaoyuechen.com:20334/api/v1/node/generateblocktime
curl -i http://es.xiaoyuechen.com:20344/api/v1/node/generateblocktime
curl -i http://es.xiaoyuechen.com:20354/api/v1/node/generateblocktime
curl -i http://es.xiaoyuechen.com:20364/api/v1/node/generateblocktime
curl -i http://es.xiaoyuechen.com:20374/api/v1/node/generateblocktime
curl -i http://es.xiaoyuechen.com:20384/api/v1/node/generateblocktime
```

#### Response example:

```
{
    "Action": "getgenerateblocktime",
    "Desc": "SUCCESS"
    "Error": 0,
    "Result": 6,
    "Version": "1.0.0"
}
```
### 2 get_conn_count

获取连接主节点数

##### GET

```
/api/v1/node/connectioncount
```

#### Request Example:

```
curl -i http://server:port/api/v1/node/connectioncount
curl -i http://es.xiaoyuechen.com:20334/api/v1/node/connectioncount
curl -i http://es.xiaoyuechen.com:20344/api/v1/node/connectioncount
curl -i http://es.xiaoyuechen.com:20354/api/v1/node/connectioncount
curl -i http://es.xiaoyuechen.com:20364/api/v1/node/connectioncount
curl -i http://es.xiaoyuechen.com:20374/api/v1/node/connectioncount
curl -i http://es.xiaoyuechen.com:20384/api/v1/node/connectioncount
```

#### Response Example:

```
{
    "Action": "getconnectioncount",
    "Desc": "SUCCESS",
    "Error": 0,
    "Result": 4,
    "Version": "1.0.0"
}
```
### 3 get_blk_txs_by_height

通过区块高度获取交易信息
返回指定区块高度内包含的所有交易信息。

##### GET

```
/api/v1/block/transactions/height/:height
```

#### Request Example:

```
curl -i http://server:port/api/v1/block/transactions/height/83637
curl -i http://es.xiaoyuechen.com:20334/api/v1/block/transactions/height/83637
curl -i http://es.xiaoyuechen.com:20344/api/v1/block/transactions/height/83637
curl -i http://es.xiaoyuechen.com:20354/api/v1/block/transactions/height/83637
curl -i http://es.xiaoyuechen.com:20364/api/v1/block/transactions/height/83637
curl -i http://es.xiaoyuechen.com:20374/api/v1/block/transactions/height/83637
curl -i http://es.xiaoyuechen.com:20384/api/v1/block/transactions/height/83637
```

#### Response Example:

```
{
    "Action":"getblocktxsbyheight",
    "Desc":"SUCCESS",
    "Error":0,
    "Result":{
        "Hash":"ea4ee6afe694b77b3b7f6f69fdd330b81b2db0888bc50c8db15fb2bfe0e4fd50",
        "Height":83637,
        "Transactions":[
            "432dd6d586b1ae240332a8ea0515bb3d205d2535326f0d9cca3ed0cb0737f7e3",
            "3164295688f9670ce3fb0b1ca3364a4a56634ffddc41c90bf35a0f02606a9d1d"
        ]
    },
    "Version":"1.0.0"
}
```
### 4 get_blk_by_height

通过区块高度获取区块详情
返回指定区块高度的区块详情

##### GET

```
/api/v1/block/details/height/:height
```

#### Request Example:

```
curl -i http://server:port/api/v1/block/details/height/83637
curl -i http://es.xiaoyuechen.com:20334/api/v1/block/details/height/83637
curl -i http://es.xiaoyuechen.com:20344/api/v1/block/details/height/83637
curl -i http://es.xiaoyuechen.com:20354/api/v1/block/details/height/83637
curl -i http://es.xiaoyuechen.com:20364/api/v1/block/details/height/83637
curl -i http://es.xiaoyuechen.com:20374/api/v1/block/details/height/83637
curl -i http://es.xiaoyuechen.com:20384/api/v1/block/details/height/83637
```

#### Response Example:

```
{
    "Action":"getblockbyheight",
    "Desc":"SUCCESS",
    "Error":0,
    "Result":{
        "Hash":"ea4ee6afe694b77b3b7f6f69fdd330b81b2db0888bc50c8db15fb2bfe0e4fd50",
        "Header":{
            "Version":0,
            "PrevBlockHash":"09b4c25e8dd40fc0f50b75a32bc3bc72264448b7a6009c5484dc4c899fb3ccd6",
            "TransactionsRoot":"238e60fc835246836ceb0576afb52ac8856169caf80efe129b8ca9f3d45ac6d0",
            "BlockRoot":"e687bc9eb7c23d22959f8ecd41bcd3cb5a67fbb80dcb9d56c5ce96dc7db9b59f",
            "Timestamp":1525768455,
            "Height":83637,
            "ConsensusData":8435973123287926373,
            "NextBookkeeper":"TAD78tgR4KGDhFJhK7atqqUNmHjEKbkPUt",
            "Bookkeepers":[
                "120202a8b34803b56c7c892031fe9c2864fd52614f633bc1f2c5bb799672400694871d",
                "120202c42c40b029f99ab29aa3eb60293bbda3c256adaba3bab986016865e1be16b2c9",
                "1202030a60ee45125cc2064d95bc35b043bbda25d7e9340006d30f3e6264582b656ecd",
                "120203b878cb1e2eda5f5a70b7c0a2f10ab4f9c42bf6578876c5c9c876ebb5063ad763"
            ],
            "SigData":[
                "01e632b8209364a346124e1de91dda29d8b1ed53eb08edeacb3419572c2fc33eb3083a687ca70b26f17a330636e2d6560f17016a0a338e63576de2b536672e28d4",
                "018afa9d5e546de8f420a80093b374dd96dfd5a2c7f895ff3cda5ecedaf26b38fc18757179c48fe7e5c83183cefb268dff01919ddeeaeb26e61e2cb383dc21d391",
                "01553ece11c7fce3987ba0af5254cd400f37bbdfac3401fa5f2a218a0397a00682eb4964bc8c34381916986c88c0870434b9f3480c06ccf179dc19583c62ca2799"
            ],
            "Hash":"ea4ee6afe694b77b3b7f6f69fdd330b81b2db0888bc50c8db15fb2bfe0e4fd50"
        },
        "Transactions":[
            {
                "Version":0,
                "Nonce":0,
                "TxType":0,
                "Payload":{
                    "Nonce":1525768455910913235
                },
                "Attributes":[

                ],
                "Fee":[

                ],
                "NetworkFee":0,
                "Sigs":[

                ],
                "Hash":"432dd6d586b1ae240332a8ea0515bb3d205d2535326f0d9cca3ed0cb0737f7e3"
            },
            {
                "Version":0,
                "Nonce":1525768454,
                "TxType":209,
                "Payload":{
                    "Code":"0000ff00000000000000000000000000000000000001087472616e736665722e0001000172ad6efa53461ac106536fae28f869e8e031b5012cf101547425b5f581d61bcd1c81615f045fb50201f4",
                    "GasLimit":0,
                    "VmType":255
                },
                "Attributes":[

                ],
                "Fee":[

                ],
                "NetworkFee":0,
                "Sigs":[
                    {
                        "PubKeys":[
                            "12020270eec86e5ec3d5eda2056d6284c981133fd0ff91edc981bdaea76511f02719e4"
                        ],
                        "M":1,
                        "SigData":[
                            "016eacb6aeae5d61413cd620783bb248765f42b459d4e80fd95747d4cff9a0fab44945b06b8ab43592c34a18b9e5bf5d7977f4bb5f976551653d388b6ec0f054f0"
                        ]
                    }
                ],
                "Hash":"3164295688f9670ce3fb0b1ca3364a4a56634ffddc41c90bf35a0f02606a9d1d"
            }
        ]
    },
    "Version":"1.0.0"
}
```
### 5 get_blk_by_hash

通过区块HASH获取区块详情
返回指定区块HASH的区块详情

##### GET

```
/api/v1/block/details/hash/:hash
```

#### Request Example:

```
curl -i http://server:port/api/v1/block/details/hash/ea4ee6afe694b77b3b7f6f69fdd330b81b2db0888bc50c8db15fb2bfe0e4fd50
curl -i http://es.xiaoyuechen.com:20334/api/v1/block/details/hash/ea4ee6afe694b77b3b7f6f69fdd330b81b2db0888bc50c8db15fb2bfe0e4fd50
curl -i http://es.xiaoyuechen.com:20344/api/v1/block/details/hash/ea4ee6afe694b77b3b7f6f69fdd330b81b2db0888bc50c8db15fb2bfe0e4fd50
curl -i http://es.xiaoyuechen.com:20354/api/v1/block/details/hash/ea4ee6afe694b77b3b7f6f69fdd330b81b2db0888bc50c8db15fb2bfe0e4fd50
curl -i http://es.xiaoyuechen.com:20364/api/v1/block/details/hash/ea4ee6afe694b77b3b7f6f69fdd330b81b2db0888bc50c8db15fb2bfe0e4fd50
curl -i http://es.xiaoyuechen.com:20374/api/v1/block/details/hash/ea4ee6afe694b77b3b7f6f69fdd330b81b2db0888bc50c8db15fb2bfe0e4fd50
curl -i http://es.xiaoyuechen.com:20384/api/v1/block/details/hash/ea4ee6afe694b77b3b7f6f69fdd330b81b2db0888bc50c8db15fb2bfe0e4fd50
```

#### Response Example:

```
{
    "Action":"getblockbyhash",
    "Desc":"SUCCESS",
    "Error":0,
    "Result":{
        "Hash":"ea4ee6afe694b77b3b7f6f69fdd330b81b2db0888bc50c8db15fb2bfe0e4fd50",
        "Header":{
            "Version":0,
            "PrevBlockHash":"09b4c25e8dd40fc0f50b75a32bc3bc72264448b7a6009c5484dc4c899fb3ccd6",
            "TransactionsRoot":"238e60fc835246836ceb0576afb52ac8856169caf80efe129b8ca9f3d45ac6d0",
            "BlockRoot":"e687bc9eb7c23d22959f8ecd41bcd3cb5a67fbb80dcb9d56c5ce96dc7db9b59f",
            "Timestamp":1525768455,
            "Height":83637,
            "ConsensusData":8435973123287926373,
            "NextBookkeeper":"TAD78tgR4KGDhFJhK7atqqUNmHjEKbkPUt",
            "Bookkeepers":[
                "120202a8b34803b56c7c892031fe9c2864fd52614f633bc1f2c5bb799672400694871d",
                "120202c42c40b029f99ab29aa3eb60293bbda3c256adaba3bab986016865e1be16b2c9",
                "1202030a60ee45125cc2064d95bc35b043bbda25d7e9340006d30f3e6264582b656ecd",
                "120203b878cb1e2eda5f5a70b7c0a2f10ab4f9c42bf6578876c5c9c876ebb5063ad763"
            ],
            "SigData":[
                "01e632b8209364a346124e1de91dda29d8b1ed53eb08edeacb3419572c2fc33eb3083a687ca70b26f17a330636e2d6560f17016a0a338e63576de2b536672e28d4",
                "018afa9d5e546de8f420a80093b374dd96dfd5a2c7f895ff3cda5ecedaf26b38fc18757179c48fe7e5c83183cefb268dff01919ddeeaeb26e61e2cb383dc21d391",
                "01553ece11c7fce3987ba0af5254cd400f37bbdfac3401fa5f2a218a0397a00682eb4964bc8c34381916986c88c0870434b9f3480c06ccf179dc19583c62ca2799"
            ],
            "Hash":"ea4ee6afe694b77b3b7f6f69fdd330b81b2db0888bc50c8db15fb2bfe0e4fd50"
        },
        "Transactions":[
            {
                "Version":0,
                "Nonce":0,
                "TxType":0,
                "Payload":{
                    "Nonce":1525768455910913235
                },
                "Attributes":[

                ],
                "Fee":[

                ],
                "NetworkFee":0,
                "Sigs":[

                ],
                "Hash":"432dd6d586b1ae240332a8ea0515bb3d205d2535326f0d9cca3ed0cb0737f7e3"
            },
            {
                "Version":0,
                "Nonce":1525768454,
                "TxType":209,
                "Payload":{
                    "Code":"0000ff00000000000000000000000000000000000001087472616e736665722e0001000172ad6efa53461ac106536fae28f869e8e031b5012cf101547425b5f581d61bcd1c81615f045fb50201f4",
                    "GasLimit":0,
                    "VmType":255
                },
                "Attributes":[

                ],
                "Fee":[

                ],
                "NetworkFee":0,
                "Sigs":[
                    {
                        "PubKeys":[
                            "12020270eec86e5ec3d5eda2056d6284c981133fd0ff91edc981bdaea76511f02719e4"
                        ],
                        "M":1,
                        "SigData":[
                            "016eacb6aeae5d61413cd620783bb248765f42b459d4e80fd95747d4cff9a0fab44945b06b8ab43592c34a18b9e5bf5d7977f4bb5f976551653d388b6ec0f054f0"
                        ]
                    }
                ],
                "Hash":"3164295688f9670ce3fb0b1ca3364a4a56634ffddc41c90bf35a0f02606a9d1d"
            }
        ]
    },
    "Version":"1.0.0"
}
```

### 6 get_blk_height

获取当前区块高度

##### GET

```
/api/v1/block/height
```

#### Request Example:

```
curl -i http://server:port/api/v1/block/height
curl -i http://es.xiaoyuechen.com:20334/api/v1/block/height
curl -i http://es.xiaoyuechen.com:20344/api/v1/block/height
curl -i http://es.xiaoyuechen.com:20354/api/v1/block/height
curl -i http://es.xiaoyuechen.com:20364/api/v1/block/height
curl -i http://es.xiaoyuechen.com:20374/api/v1/block/height
curl -i http://es.xiaoyuechen.com:20384/api/v1/block/height
```


#### Response Example:

```
{
    "Action": "getblockheight",
    "Desc": "SUCCESS",
    "Error": 0,
    "Result": 327,
    "Version": "1.0.0"
}
```

### 7 get_blk_hash

获取指定区块高度的HASH值

##### GET

```
/api/v1/block/hash/:height
```

#### Request Example:

```
curl -i http://server:port/api/v1/block/hash/83637
curl -i http://es.xiaoyuechen.com:20334/api/v1/block/hash/83637
curl -i http://es.xiaoyuechen.com:20344/api/v1/block/hash/83637
curl -i http://es.xiaoyuechen.com:20354/api/v1/block/hash/83637
curl -i http://es.xiaoyuechen.com:20364/api/v1/block/hash/83637
curl -i http://es.xiaoyuechen.com:20374/api/v1/block/hash/83637
curl -i http://es.xiaoyuechen.com:20384/api/v1/block/hash/83637
```

#### Response Example:

```
{
    "Action":"getblockhash",
    "Desc":"SUCCESS",
    "Error":0,
    "Result":"ea4ee6afe694b77b3b7f6f69fdd330b81b2db0888bc50c8db15fb2bfe0e4fd50",
    "Version":"1.0.0"
}
```

### 8 get_tx

通过交易HASH获取交易详情

##### GET

```
/api/v1/transaction/:hash
```

####Request Example:

```
curl -i http://server:port/api/v1/transaction/3164295688f9670ce3fb0b1ca3364a4a56634ffddc41c90bf35a0f02606a9d1d
curl -i http://es.xiaoyuechen.com:20334/api/v1/transaction/3164295688f9670ce3fb0b1ca3364a4a56634ffddc41c90bf35a0f02606a9d1d
curl -i http://es.xiaoyuechen.com:20344/api/v1/transaction/3164295688f9670ce3fb0b1ca3364a4a56634ffddc41c90bf35a0f02606a9d1d
curl -i http://es.xiaoyuechen.com:20354/api/v1/transaction/3164295688f9670ce3fb0b1ca3364a4a56634ffddc41c90bf35a0f02606a9d1d
curl -i http://es.xiaoyuechen.com:20364/api/v1/transaction/3164295688f9670ce3fb0b1ca3364a4a56634ffddc41c90bf35a0f02606a9d1d
curl -i http://es.xiaoyuechen.com:20374/api/v1/transaction/3164295688f9670ce3fb0b1ca3364a4a56634ffddc41c90bf35a0f02606a9d1d
curl -i http://es.xiaoyuechen.com:20384/api/v1/transaction/3164295688f9670ce3fb0b1ca3364a4a56634ffddc41c90bf35a0f02606a9d1d
```
#### Response Example:

```
{
    "Action":"gettransaction",
    "Desc":"SUCCESS",
    "Error":0,
    "Result":{
        "Version":0,
        "Nonce":1525768454,
        "TxType":209,
        "Payload":{
            "Code":"0000ff00000000000000000000000000000000000001087472616e736665722e0001000172ad6efa53461ac106536fae28f869e8e031b5012cf101547425b5f581d61bcd1c81615f045fb50201f4",
            "GasLimit":0,
            "VmType":255
        },
        "Attributes":[

        ],
        "Fee":[

        ],
        "NetworkFee":0,
        "Sigs":[
            {
                "PubKeys":[
                    "12020270eec86e5ec3d5eda2056d6284c981133fd0ff91edc981bdaea76511f02719e4"
                ],
                "M":1,
                "SigData":[
                    "016eacb6aeae5d61413cd620783bb248765f42b459d4e80fd95747d4cff9a0fab44945b06b8ab43592c34a18b9e5bf5d7977f4bb5f976551653d388b6ec0f054f0"
                ]
            }
        ],
        "Hash":"3164295688f9670ce3fb0b1ca3364a4a56634ffddc41c90bf35a0f02606a9d1d"
    },
    "Version":"1.0.0"
}
```

### 9 post_raw_tx

发送交易

##### POST

```
/api/v1/transaction
```

#### Request Example:

```
curl -H "Content-Type: application/json" -X POST -d '{"Action":"sendrawtransaction", "Version":"1.0.0","Data":"00d00000000080fdcf2b0138c56b6c766b00527ac46c766b51527ac46151c56c766b52527ac46c766b00c31052656749644279507..."}'  http://server:port/api/v1/transaction
```

#### Post Params:

```
{
    "Action":"sendrawtransaction",
    "Version":"1.0.0",
    "Data":"80000001195876cb34364dc38b730077156c6bc3a7fc570044a66fbfeeea56f71327e8ab0000029b7cffdaa674beae0f930ebe6085af9093e5fe56b34a5c220ccdcf6efc336fc500c65eaf440000000f9a23e06f74cf86b8827a9108ec2e0f89ad956c9b7cffdaa674beae0f930ebe6085af9093e5fe56b34a5c220ccdcf6efc336fc50092e14b5e00000030aab52ad93f6ce17ca07fa88fc191828c58cb71014140915467ecd359684b2dc358024ca750609591aa731a0b309c7fb3cab5cd0836ad3992aa0a24da431f43b68883ea5651d548feb6bd3c8e16376e6e426f91f84c58232103322f35c7819267e721335948d385fae5be66e7ba8c748ac15467dcca0693692dac"
}
```
Take the "AddAttribute" in the IdContract contract as an example

1. build parameter

```
acct := account.Open(account.WALLET_FILENAME, []byte("passwordtest"))
acc, err := acct.GetDefaultAccount()
pubkey := keypair.SerializePublicKey(acc.PubKey())
funcName := "AddAttribute"
paras := []interface{}{[]byte("did:ont:" + acc.Address.ToBase58()),[]byte("key1"),[]byte("bytes"),[]byte("value1"),pubkey}
builder := neovm.NewParamsBuilder(new(bytes.Buffer))
err = BuildSmartContractParamInter(builder, []interface{}{funcName, params})
codeParams := builder.ToArray()
op_verify,_ := common.HexToBytes("69")
codeaddress,_ := common.HexToBytes("8055b362904715fd84536e754868f4c8d27ca3f6")
codeParams = BytesCombine(codeParams,op_verify)
codeParams = BytesCombine(codeParams,codeaddress)

func BytesCombine(pBytes ...[]byte) []byte {
	len := len(pBytes)
	s := make([][]byte, len)
	for index := 0; index < len; index++ {
		s[index] = pBytes[index]
	}
	sep := []byte("")
	return bytes.Join(s, sep)
}
```
funcName:the smartcontract function name to be called, params: contract function required parameters, codeAddress: smartcontract address

2. build transaction
```
tx := utils.NewInvokeTransaction(vmtypes.VmCode{
		VmType: vmtypes.NEOVM,
		Code:   codeParams,
	})
	tx.Nonce = uint32(time.Now().Unix())
```

3. sign transaction

```
hash := tx.Hash()
sign, _ := signature.Sign(acc.PrivateKey, hash[:])
tx.Sigs = append(tx.Sigs, &ctypes.Sig{
    PubKeys: []keypair.PublicKey{acc.PublicKey},
    M:       1,
    SigData: [][]byte{sign},
})
```

4. Convert transactions to hexadecimal strings
```
txbf := new(bytes.Buffer)
err = tx.Serialize(txbf);
common.ToHexString(txbf.Bytes())
```

Related struct
```
type Transaction struct {
	Version    byte
	TxType     TransactionType
	Nonce      uint32
	Payload    Payload
	Attributes []*TxAttribute
	Fee        []*Fee
	NetWorkFee common.Fixed64
	Sigs       []*Sig

	hash *common.Uint256
}

type Sig struct {
	PubKeys []keypair.PublicKey
	M       uint8
	SigData [][]byte
}
```

#### Response
```
{
    "Action": "sendrawtransaction",
    "Desc": "SUCCESS",
    "Error": 0,
    "Result": "22471ab3f4b4307a99f00c9a717dbf8b26f5bf63bf47f9c560477da8181de777",
    "Version": "1.0.0"
}
```
> Result: txhash

### 10 get_storage

根据合约脚本散列和存储键返回存储的值。

##### GET
```
/api/v1/storage/:hash/:key
```
##### Request Example
```
curl -i http://localhost:20384/api/v1/storage/ff00000000000000000000000000000000000001/0144587c1094f6929ed7362d6328cffff4fb4da2
curl -i http://es.xiaoyuechen.com:20334/api/v1/storage/ff00000000000000000000000000000000000001/0144587c1094f6929ed7362d6328cffff4fb4da2
curl -i http://es.xiaoyuechen.com:20344/api/v1/storage/ff00000000000000000000000000000000000001/012cf101547425b5f581d61bcd1c81615f045fb5
curl -i http://es.xiaoyuechen.com:20354/api/v1/storage/ff00000000000000000000000000000000000001/012cf101547425b5f581d61bcd1c81615f045fb5
curl -i http://es.xiaoyuechen.com:20364/api/v1/storage/ff00000000000000000000000000000000000001/012cf101547425b5f581d61bcd1c81615f045fb5
curl -i http://es.xiaoyuechen.com:20374/api/v1/storage/ff00000000000000000000000000000000000001/012cf101547425b5f581d61bcd1c81615f045fb5
curl -i http://es.xiaoyuechen.com:20384/api/v1/storage/ff00000000000000000000000000000000000001/012cf101547425b5f581d61bcd1c81615f045fb5
```
#### Response
```
{
    "Action": "getstorage",
    "Desc": "SUCCESS",
    "Error": 0,
    "Result": "58d15e17628000",
    "Version": "1.0.0"
}
```
> Result:Returns the stored value according to the contract script hashes and stored key.

### 11 get_balance

返回Base58编码账户地址的余额

##### GET
```
/api/v1/balance/:addr
```
> addr: Base58 encoded account address

##### Request Example
```
curl -i http://localhost:20384/api/v1/balance/TA5uYzLU2vBvvfCMxyV2sdzc9kPqJzGZWq
curl -i http://es.xiaoyuechen.com:20334/api/v1/balance/TA5RWqSBZBpb8UvEfnGapRGcXnSaoaZ1Yu
curl -i http://es.xiaoyuechen.com:20344/api/v1/balance/TA5RWqSBZBpb8UvEfnGapRGcXnSaoaZ1Yu
curl -i http://es.xiaoyuechen.com:20354/api/v1/balance/TA5RWqSBZBpb8UvEfnGapRGcXnSaoaZ1Yu
curl -i http://es.xiaoyuechen.com:20364/api/v1/balance/TA5RWqSBZBpb8UvEfnGapRGcXnSaoaZ1Yu
curl -i http://es.xiaoyuechen.com:20374/api/v1/balance/TA5RWqSBZBpb8UvEfnGapRGcXnSaoaZ1Yu
curl -i http://es.xiaoyuechen.com:20384/api/v1/balance/TA5RWqSBZBpb8UvEfnGapRGcXnSaoaZ1Yu
```

#### Response
```
{
    "Action":"getbalance",
    "Desc":"SUCCESS",
    "Error":0,
    "Result":{
        "ont":"2000",
        "ong":"0",
        "ong_appove":"10050840000"
    },
    "Version":"1.0.0"
}
```
### 12 get_contract_state

根据合约脚本哈希，查询合约信息。

##### GET

```
/api/v1/contract/:hash
```

#### Request Example:

```
curl -i http://server:port/api/v1/contract/fff49c809d302a2956e9dc0012619a452d4b846c
curl -i http://es.xiaoyuechen.com:20334/api/v1/contract/ff00000000000000000000000000000000000001
curl -i http://es.xiaoyuechen.com:20344/api/v1/contract/ff00000000000000000000000000000000000001
curl -i http://es.xiaoyuechen.com:20354/api/v1/contract/ff00000000000000000000000000000000000001
curl -i http://es.xiaoyuechen.com:20364/api/v1/contract/ff00000000000000000000000000000000000001
curl -i http://es.xiaoyuechen.com:20374/api/v1/contract/ff00000000000000000000000000000000000001
curl -i http://es.xiaoyuechen.com:20384/api/v1/contract/ff00000000000000000000000000000000000001
```

#### Response Example:

```
{
    "Action":"getcontract",
    "Desc":"SUCCESS",
    "Error":0,
    "Result":{
        "VmType":255,
        "Code":"ff00000000000000000000000000000000000001",
        "NeedStorage":true,
        "Name":"ONT",
        "CodeVersion":"1.0",
        "Author":"Ontology Team",
        "Email":"contact@ont.io",
        "Description":"Ontology Network ONT Token"
    },
    "Version":"1.0.0"
}
```

#### 13 get_smtcode_evt_txs

通过区块高度获取智能合约事件的交易哈希
获取指定区块高度的智能合约事件的交易哈希列表

##### GET

```
/api/v1/smartcode/event/transactions/:height
```

#### Example usage:

```
curl -i http://localhost:20384/api/v1/smartcode/event/transactions/900
curl -i http://es.xiaoyuechen.com:20334/api/v1/smartcode/event/transactions/83637
curl -i http://es.xiaoyuechen.com:20344/api/v1/smartcode/event/transactions/83637
curl -i http://es.xiaoyuechen.com:20354/api/v1/smartcode/event/transactions/83637
curl -i http://es.xiaoyuechen.com:20364/api/v1/smartcode/event/transactions/83637
curl -i http://es.xiaoyuechen.com:20374/api/v1/smartcode/event/transactions/83637
curl -i http://es.xiaoyuechen.com:20384/api/v1/smartcode/event/transactions/83637
```

#### response
```
{
    "Action": "getsmartcodeeventbyheight",
    "Desc": "SUCCESS",
    "Error": 0,
    "Result": [
        "3164295688f9670ce3fb0b1ca3364a4a56634ffddc41c90bf35a0f02606a9d1d"
    ],
    "Version": "1.0.0"
}
```
> Note: result is the txHash list.

### 14 get_smtcode_evts

通过交易哈希获取智能合约事件

##### GET
```
/api/v1/smartcode/event/txhash/:hash
```
#### Request Example:
```
curl -i http://localhost:20384/api/v1/smartcode/event/txhash/3e23cf222a47739d4141255da617cd42925a12638ac19cadcc85501f907972c8
curl -i http://es.xiaoyuechen.com:20334/api/v1/smartcode/event/txhash/3164295688f9670ce3fb0b1ca3364a4a56634ffddc41c90bf35a0f02606a9d1d
curl -i http://es.xiaoyuechen.com:20344/api/v1/smartcode/event/txhash/3164295688f9670ce3fb0b1ca3364a4a56634ffddc41c90bf35a0f02606a9d1d
curl -i http://es.xiaoyuechen.com:20354/api/v1/smartcode/event/txhash/3164295688f9670ce3fb0b1ca3364a4a56634ffddc41c90bf35a0f02606a9d1d
curl -i http://es.xiaoyuechen.com:20364/api/v1/smartcode/event/txhash/3164295688f9670ce3fb0b1ca3364a4a56634ffddc41c90bf35a0f02606a9d1d
curl -i http://es.xiaoyuechen.com:20374/api/v1/smartcode/event/txhash/3164295688f9670ce3fb0b1ca3364a4a56634ffddc41c90bf35a0f02606a9d1d
curl -i http://es.xiaoyuechen.com:20384/api/v1/smartcode/event/txhash/3164295688f9670ce3fb0b1ca3364a4a56634ffddc41c90bf35a0f02606a9d1d
```
#### Response:
```
{
    "Action":"getsmartcodeeventbyhash",
    "Desc":"SUCCESS",
    "Error":0,
    "Result":[
        {
            "TxHash":"3164295688f9670ce3fb0b1ca3364a4a56634ffddc41c90bf35a0f02606a9d1d",
            "ContractAddress":"ff00000000000000000000000000000000000001",
            "States":[
                "transfer",
                "TA6s4BUfg7AWNHwJCMxLFMxhgCP5szWHmu",
                "TA5RWqSBZBpb8UvEfnGapRGcXnSaoaZ1Yu",
                500
            ]
        }
    ],
    "Version":"1.0.0"
}
```
### 15 get_blk_hgt_by_txhash

通过交易哈希获取区块高度

##### GET
```
/api/v1/block/height/txhash/:hash
```
#### Request Example:
```
curl -i http://localhost:20384/api/v1/block/height/txhash/3164295688f9670ce3fb0b1ca3364a4a56634ffddc41c90bf35a0f02606a9d1d
curl -i http://es.xiaoyuechen.com:20334/api/v1/block/height/txhash/3164295688f9670ce3fb0b1ca3364a4a56634ffddc41c90bf35a0f02606a9d1d
curl -i http://es.xiaoyuechen.com:20344/api/v1/block/height/txhash/3164295688f9670ce3fb0b1ca3364a4a56634ffddc41c90bf35a0f02606a9d1d
curl -i http://es.xiaoyuechen.com:20354/api/v1/block/height/txhash/3164295688f9670ce3fb0b1ca3364a4a56634ffddc41c90bf35a0f02606a9d1d
curl -i http://es.xiaoyuechen.com:20364/api/v1/block/height/txhash/3164295688f9670ce3fb0b1ca3364a4a56634ffddc41c90bf35a0f02606a9d1d
curl -i http://es.xiaoyuechen.com:20374/api/v1/block/height/txhash/3164295688f9670ce3fb0b1ca3364a4a56634ffddc41c90bf35a0f02606a9d1d
curl -i http://es.xiaoyuechen.com:20384/api/v1/block/height/txhash/3164295688f9670ce3fb0b1ca3364a4a56634ffddc41c90bf35a0f02606a9d1d
```
#### Response
```
{
    "Action":"getblockheightbytxhash",
    "Desc":"SUCCESS",
    "Error":0,
    "Result":83637,
    "Version":"1.0.0"
}
```

### 16 get_merkle_proof

获取merkle证明

##### GET
```
/api/v1/merkleproof/:hash
```
#### Request Example:
```
curl -i http://localhost:20384/api/v1/merkleproof/3e23cf222a47739d4141255da617cd42925a12638ac19cadcc85501f907972c8
curl -i http://es.xiaoyuechen.com:20334/api/v1/merkleproof/3164295688f9670ce3fb0b1ca3364a4a56634ffddc41c90bf35a0f02606a9d1d
curl -i http://es.xiaoyuechen.com:20344/api/v1/merkleproof/3164295688f9670ce3fb0b1ca3364a4a56634ffddc41c90bf35a0f02606a9d1d
curl -i http://es.xiaoyuechen.com:20354/api/v1/merkleproof/3164295688f9670ce3fb0b1ca3364a4a56634ffddc41c90bf35a0f02606a9d1d
curl -i http://es.xiaoyuechen.com:20364/api/v1/merkleproof/3164295688f9670ce3fb0b1ca3364a4a56634ffddc41c90bf35a0f02606a9d1d
curl -i http://es.xiaoyuechen.com:20374/api/v1/merkleproof/3164295688f9670ce3fb0b1ca3364a4a56634ffddc41c90bf35a0f02606a9d1d
curl -i http://es.xiaoyuechen.com:20384/api/v1/merkleproof/3164295688f9670ce3fb0b1ca3364a4a56634ffddc41c90bf35a0f02606a9d1d
```
#### Response
```
{
    "Action":"getmerkleproof",
    "Desc":"SUCCESS",
    "Error":0,
    "Result":{
        "Type":"MerkleProof",
        "TransactionsRoot":"238e60fc835246836ceb0576afb52ac8856169caf80efe129b8ca9f3d45ac6d0",
        "BlockHeight":83637,
        "CurBlockRoot":"0835a5ce121daddef480e3aa8d9ff1369b8a4b4e19e30433a38c5c28a3f81ba1",
        "CurBlockHeight":84298,
        "TargetHashes":[
            "2de6af7f4a88f3898b598428aa35f754863d8eaaa5b41d260fa55efb29ca6ab9",
            "ba9c36924ce38081c37457cde4036983040420154aeb42e59865c0316be47713",
            "8b4a14dd00cc7b040eb8d5053a026d2755a2bd03e1c92c65d67feaf10ab064d0",
            "4c4f38951c92cebf67962eeca83b8d9a0ed3cce694d4cc678b1bf2ac131f6792",
            "7755f674f09453bf033c7e1a17210692fe6a6d69a2ea2e9532c445c0f62fe556",
            "e156319a1ca0b2aaeee65543f72dcf8f646872ea25e91b512f712918de83feaf",
            "f130c9a1ff8da4d89decc5fbb5d8cffb120ec7c1819eca681b90b1420a8ffe4b",
            "77dfcb970121faf37b27d4776cd0c714edf4edf9d4b0d4d7fec58b6cc435caa0",
            "2041778408bbea7615d22debf6c81d0eb886d813bd73459768e7e7bde1fd62a2",
            "a3bcd8f7973aad4d3fa2cd60d8300e757231cc27a90c06a94e7cfc9136b8733b",
            "42e3764dd40b081976f33b3ecd2ce23aea30396d88786db8d835cad6201067e7",
            "6d21e227c4a8e053200525a01bd1a6b32093949517b96387ca3755932211bd53",
            "8acdaa9eafc2669f45da6e83a8864130ba1ac893fde14b7f4ba022c8851a52ab",
            "4b506fdb278e19b97cd6ae5598c6fb23d6024cd7a8a74eded08365856d0bb585"
        ]
    },
    "Version":"1.0.0"
}
```

## Errorcode

| Field | Type | Description |
| :--- | :--- | :--- |
| 0 | int64 | SUCCESS |
| 41001 | int64 | SESSION\_EXPIRED: invalided or expired session |
| 41002 | int64 | SERVICE\_CEILING: reach service limit |
| 41003 | int64 | ILLEGAL\_DATAFORMAT: illegal dataformat |
| 41004 | int64 | INVALID\_VERSION: invalid version |
| 42001 | int64 | INVALID\_METHOD: invalid method |
| 42002 | int64 | INVALID\_PARAMS: invalid params |
| 43001 | int64 | INVALID\_TRANSACTION: invalid transaction |
| 43002 | int64 | INVALID\_ASSET: invalid asset |
| 43003 | int64 | INVALID\_BLOCK: invalid block |
| 44001 | int64 | UNKNOWN\_TRANSACTION: unknown transaction |
| 44002 | int64 | UNKNOWN\_ASSET: unknown asset |
| 44003 | int64 | UNKNOWN\_BLOCK: unknown block |
| 45001 | int64 | INTERNAL\_ERROR: internel error |
| 47001 | int64 | SMARTCODE\_ERROR: smartcode error |
