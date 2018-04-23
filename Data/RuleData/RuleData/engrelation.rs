/*---------------- HELPER PREDICATES -------------*/
Male(person) 							//{0} is male 
Female(person)							//{0} is female
Older(person,person) 					//{0} is older than {1}
Younger(person,person)  				//{0} is younger than {1}

/*---------------- HELPER RELS -------------------*/
Older(x,y) <=> Younger(y,x)
Older(x,y) ^ Older(y,z) => Older(x,z)
Younger(x,y) ^ Younger(y,z) => Younger(x,z)

/*---------------- RELATION PREDICATES -----------*/ 

Spouse(person,person)					//{0}是{1}的配偶
Wife(person,person) 					//{0}是{1}的妻子
Husband(person,person)					//{0}是{1}的丈夫

Parent(person,person)					//{0}是{1}的父母
Father(person,person)					//{0}是{1}的父亲
Mother(person,person)					//{0}是{1}的母亲

Children(person,person)					//{0}是{1}的子女
Son(person,person)						//{0}是{1}的儿子
Daughter(person,person)					//{0}是{1}的女儿

Sibling(person,person)					//{0}是{1}的兄弟姐们
Oldersibling(person,person)				//{0}是{1}的哥哥姐姐
Youngersibling(person,person)			//{0}是{1}的弟弟妹妹
Malesibling(person,person)				//{0}是{1}的兄弟
Femalesibling(person,person)			//{0}是{1}的姐妹
Olderbrother(person,person)				//{0}是{1}的哥哥
Youngerbrother(person,person)			//{0}是{1}的弟弟
Oldersister(person,person)				//{0}是{1}的姐姐
Youngersister(person,person)			//{0}是{1}的妹妹

Relative(person,person)					//{0}是{1}的亲戚

Friend(person,person)					//{0}是{1}的朋友

Grandchildren(person,person)			//{0}是{1}的孙辈
Grandson(person,person)					//{0}是{1}的孙子
Granddaughter(person,person)			//{0}是{1}的孙女

Grandparent(person,person)				//{0}是{1}的祖辈
Grandfather(person,person)				//{0}是{1}的祖父
Grandmother(person,person)				//{0}是{1}的祖母

Childinlaw(person,person)				//{0}是{1}的儿媳女婿
Soninlaw(person,person)					//{0}是{1}的女婿
Daughterinlaw(person,person)			//{0}是{1}的儿媳

Parentinlaw(person,person)				//{0}是{1}的公婆岳父母
Fatherinlaw(person,person)				//{0}是{1}的岳父公公
Motherinlaw(person,person)				//{0}是{1}的岳母婆婆

/*---------------- INVERSE -----------------------*/

//x是y的妻子 <=> y是x的丈夫
Wife(x,y) <=> Husband(y,x)

//x是y的子女 <=> y是x的父母
Children(x,y) <=> Parent(y,x)

//x是y的孙辈 <=> y是x的祖辈
Grandchildren(x,y) <=> Grandparent(y,x)

//x是y的公婆岳父母 <=> y是x的儿媳女婿
Parentinlaw(x,y) <=> Childinlaw(y,x)

//x是y的哥哥姐姐 <=> y是x的弟弟妹妹
Oldersibling(x,y) <=> Youngersibling(y,x)

/*---------------- BI-DIRECTION ------------------*/

//夫妻
Spouse(x,y) <=> Spouse(y,x)
Sibling(x,y) <=> Sibling(y,x)
Friend(x,y) <=> Friend(y,x)
Relative(x,y) <=> Relative(y,x)

/*---------------- INFERENCE ---------------------*/
//x是y的夫妻 ^ x是男 ^ y是女 <=> x是y的丈夫
Spouse(x,y) ^ Male(x) <=> Husband(x,y)
Spouse(x,y) ^ Female(x) <=> Wife(x,y)

//父亲 母亲 << 父母
Parent(x,y) ^ Male(x) <=> Father(x,y)
Parent(x,y) ^ Female(x) <=> Mother(x,y)

//x是y的子女 ^ x是男 <=> x是y的儿子
Children(x,y) ^ Male(x) <=> Son(x,y)
Children(x,y) ^ Female(x) <=> Daughter(x,y)

//x是y的子女 ^ y是男 <=> y是x的父亲
Children(x,y) ^ Male(y) <=> Father(y,x)
Children(x,y) ^ Female(y) <=> Mother(y,x)

Parent(x,y) ^ Parent(y,z) => Grandparent(x,z)
Grandparent(x,y) ^ Male(x) <=> Grandfather(x,y)
Grandparent(x,y) ^ Female(x) <=> Grandmother(x,y)

Children(x,y) ^ Children(y,z) => Grandchildren(x,z)
Grandchildren(x,y) ^ Male(x) => Grandson(x,y)
Grandchildren(x,y) ^ Female(x) => Granddaughter(x,y)

//x是y的父母 ^ z是x的父亲 => z是y的祖父
Parent(x,y) ^ Father(z,x) => Grandfather(z,y)
Parent(x,y) ^ Mother(z,x) => Grandmother(z,y)

//x是y的孩子 ^ z是x的配偶 => z是y的儿媳女婿
Children(x,y) ^ Spouse(z,x) => Childinlaw(z,y)
Childinlaw(x,y) ^ Male(x) => Soninlaw(x,y)
Childinlaw(x,y) ^ Female(x) => Daughterinlaw(x,y)

//x是y的父母 ^ y是z的配偶
Parent(x,y) ^ Spouse(y,z) => Parentinlaw(x,z)
Parentinlaw(x,y) ^ Male(x) => Fatherinlaw(x,y)
Parentinlaw(x,y) ^ Female(x) => Motherinlaw(x,y)

//DEFINE 兄弟姊妹
//x是y的父母 ^ x是z的父母 => y是z的兄弟姊妹
Parent(x,y) ^ Parent(x,z) => Sibling(y,z)

//DEFINE 兄弟
//x是y的兄弟姊妹 ^ x是男 => x是y的兄弟
Sibling(x,y) ^ Male(x) <=> Malesibling(x,y)
Sibling(x,y) ^ Female(x) <=> Femalesibling(x,y)
Sibling(x,y) ^ Older(x,y) <=> Oldersibling(x,y)
Sibling(x,y) ^ Younger(x,y) <=> Youngersibling(x,y)

Malesibling(x,y) ^ Older(x,y) <=> Olderbrother(x,y)
Malesibling(x,y) ^ Younger(x,y) <=> Youngerbrother(x,y)
Femalesibling(x,y) ^ Older(x,y) <=> Oldersister(x,y)
Femalesibling(x,y) ^ Younger(x,y) <=> Youngersister(x,y)
Youngersibling(x,y) ^ Male(x) <=> Youngerbrother(x,y)
Youngersibling(x,y) ^ Female(x) <=> Youngersister(x,y)
Oldersibling(x,y) ^ Male(x) <=> Olderbrother(x,y)
Oldersibling(x,y) ^ Female(x) <=> Oldersister(x,y)		//姐姐：哥哥姐姐中的女性

/*--------------- HELPER FUNCTION --------------*/

//Relationship for gender inference
Spouse(x,y) ^ Male(x) => Female(y)
Spouse(x,y) ^ Female(x) => Male(y)
Wife(x,y) => Female(x)
Wife(x,y) => Male(y)
Husband(x,y) => Male(x)
Husband(x,y) => Female(y)
Daughterinlaw(x,y) => Female(x)
Soninlaw(x,y) => Male(x)
Father(x,y) => Male(x)
Father(x,y) => Older(x,y)
Mother(x,y) => Female(x)
Mother(x,y) => Older(x,y)
Son(x,y) => Male(x)
Daughter(x,y) => Female(x)
Grandfather(x,y) => Male(x)
Grandmother(x,y) => Female(x)
Grandson(x,y) => Male(x)
Granddaughter(x,y) => Female(x)
Motherinlaw(x,y) => Female(x)
Fatherinlaw(x,y) => Male(x)
Malesibling(x,y) => Male(x)
Femalesibling(x,y) => Female(x)
Olderbrother(x,y) => Male(x)
Youngerbrother(x,y) => Male(x)
Oldersister(x,y) => Female(x)
Youngersister(x,y) => Female(x)

//x是y的子女 => x是y的年轻人
Children(x,y) => Younger(x,y)
Parent(x,y) => Older(x,y)
Grandchildren(x,y) => Younger(x,y)
Grandparent(x,y) => Older(x,y)
Oldersibling(x,y) => Older(x,y)
Youngersibling(x,y) => Younger(x,y)

