Almamater(person,org) 			//{0}是{1}的学生
Alumns(person,person,org)		//{0}是{1}的{2}校友

Almamater(x,y) ^ Almamater(z,y) => Alumns(x,z,y)

Alumns(x,y,z) <=> Alumns(y,x,z)
