package com.example.tp5formscomposer

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.ClickableText
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.Email
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Blue
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.tp5formscomposer.ui.theme.TP5FormsComposerTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TP5FormsComposerTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    Navigation()
                }
            }
        }
    }

}

@Composable
fun Navigation(){

    //Définir l'état de la classe de navigation
    val navController= rememberNavController()

    //définir la navigation et le futur destination
    NavHost(navController = navController, startDestination = "dest" ){
        //1ere iteration(destination)
        composable("dest"){
            Connexion(navController=navController)
        }

        //la méthode aff2 avec le nom et l'email et le mot de passe de SignUp
        composable("aff2/{name}/{email}/{password}",arguments = listOf(navArgument("name"){
            type= NavType.StringType
            defaultValue="test"
            nullable= true
        }))
        {
                entry -> aff2(name = entry.arguments?.getString("name"),email = entry.arguments?.getString("email"),pwd = entry.arguments?.getString("password"))
        }

        //le nom et l'email et et le mot de passe de Connexion
        composable("login/{name}/{password}",arguments = listOf(navArgument("name"){
            type= NavType.StringType
            defaultValue="test"
            nullable= true
        }))
        {
                entry -> aff(name = entry.arguments?.getString("name"),pwd = entry.arguments?.getString("password"))
        }

        //ne retourne rien pour les 2 pages retournes vides
        composable("login",arguments = listOf(navArgument("name"){
            type= NavType.StringType
            defaultValue="test"
            nullable= true
        })){ singup(navController = navController)}
    }

}

@Composable
fun Connexion(navController: NavController){
    Box(modifier = Modifier
        .fillMaxSize()
        .padding(20.dp)){
        text1()
    }

    Column(verticalArrangement = Arrangement.Center,
        modifier = Modifier
            .fillMaxWidth()
            .padding(50.dp)) {

        //outlinedtext field email
        var value1 by remember { mutableStateOf("") }

        Spacer(modifier = Modifier.height(50.dp))

        OutlinedTextField(
            value = value1,
            leadingIcon = {
                Icon(
                    imageVector = Icons.Default.Email,
                    contentDescription = "email"
                )
            },
            onValueChange = { value1= it },
            placeholder = { Text("Email") },
            label = { Text("Email") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),

            modifier = Modifier
                .padding(all = 5.dp)
                .fillMaxWidth(),

            )
        //outlinedtext field password
        var value2 by remember { mutableStateOf("") };

        OutlinedTextField(
            value = value2,
            onValueChange = { value2 = it },
            placeholder = { Text("********") },
            label = { Text("Password") },
            modifier = Modifier
                .padding(all = 5.dp)
                .fillMaxWidth(),

            //hidden password
            visualTransformation = PasswordVisualTransformation()

        )
        Spacer(modifier = Modifier.height(20.dp))

        Checked()//appel de la methode checked qui contient rm me et forg pass?

        Spacer(modifier = Modifier.height(20.dp))

        Button(
            onClick = {navController.navigate("login/{$value1}/{$value2}")},Modifier.fillMaxWidth()

        ) {
            Icon(
                Icons.Filled.AccountBox,
                contentDescription = "login",
                modifier = Modifier.size(ButtonDefaults.IconSize)
            )
            Spacer(Modifier.size(ButtonDefaults.IconSpacing))
            Text("Login")
        }

        Spacer(modifier = Modifier.height(20.dp))

        Row() {
            Column() {
                Text(text = "Don't have an account? ")
            }
            Column() {
                ClickableText(text = AnnotatedString(text = "sign up", SpanStyle(color= Blue)),
                    onClick = {navController.navigate("login")}
                )
            }
        }

    }
}

//pour le titre du 1ere page
@Composable
fun text1() {
    Row() {
    Text(
        color = Color.Magenta,
        text = "Sign In",
        fontSize = 29.sp,
        fontWeight = FontWeight.Bold
    )
   }
}

@Composable
fun Checked(){
    var checkedBox by remember{ mutableStateOf(false)}
    Row() {
        Checkbox(checked =checkedBox,
            onCheckedChange = { checkedBox = it }
        )

        Column() {
            Text(text = " Remember Me")
        }

        Spacer(modifier = Modifier.width(30.dp))

        Column() {
            Text(text = "Forgot Password?")
        }

    }

}

//pour le titre du 2eme page
@Composable
fun text2(){
    Row() {
        Text(
            color = Color.Magenta,
            text = "Sign Up Now",
            fontSize = 29.sp,
            fontWeight = FontWeight.Bold
        )
    }
}

@Composable
fun singup(navController: NavController){
    Box(modifier = Modifier
        .fillMaxSize()
        .padding(20.dp)){
        text2()
    }

    Column(verticalArrangement = Arrangement.Center,
        modifier = Modifier
            .fillMaxWidth()
            .padding(50.dp)
    ) {
        //username
        var value1 by remember { mutableStateOf("") }

        Spacer(modifier = Modifier.height(50.dp))

        Text(text = "User Name")
        OutlinedTextField(
            value = value1,
            onValueChange = { value1 = it },
            placeholder = { Text("UserName") },
            label = { Text("User Name") },
            modifier = Modifier
                .padding(all = 10.dp)
                .fillMaxWidth()

        )
        Spacer(modifier = Modifier.height(20.dp))

        //Email
        var value2 by remember { mutableStateOf("") }

        Text(text = "Email")
        OutlinedTextField(
            value = value2,
            leadingIcon = { Icon(imageVector = Icons.Default.Email, contentDescription = "email") },
            onValueChange = { value2 = it },
            placeholder = { Text("name@gmail.com") },
            label = { Text("Email") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),

            modifier = Modifier
                .padding(all = 10.dp)
                .fillMaxWidth(),

            )

        Spacer(modifier = Modifier.height(20.dp))

        //pwd
        var value3 by remember { mutableStateOf("") }

        Text(text = "Password")
        OutlinedTextField(
            value = value3,
            onValueChange = { value3 = it },
            placeholder = { Text("********") },
            label = { Text("password") },
            modifier = Modifier
                .padding(all = 10.dp)
                .fillMaxWidth(),

            //hidden password
            visualTransformation = PasswordVisualTransformation()

        )

        Spacer(modifier = Modifier.height(20.dp))

        //cpwd
        var value4 by remember { mutableStateOf("") }

        Text(text = "Confirm password")
        OutlinedTextField(
            value = value4,
            onValueChange = { value4 = it },
            placeholder = { Text("********") },
            label = { Text("Confirm password") },
            modifier = Modifier
                .padding(all = 10.dp)
                .fillMaxWidth(),

            //hidden password
            visualTransformation = PasswordVisualTransformation()

        )

        Spacer(modifier = Modifier.height(20.dp))

        Button(
            onClick = {navController.navigate("aff2/{$value1}/{$value2}/{$value3}")},
            Modifier.fillMaxWidth()

        ) {
            Icon(
                Icons.Filled.AccountBox,
                contentDescription = "SIGN UP",
                modifier = Modifier.size(ButtonDefaults.IconSize)
            )
            Spacer(Modifier.size(ButtonDefaults.IconSpacing))
            Text("SIGN UP")
        }

        Spacer(modifier = Modifier.height(20.dp))

        Row() {

            Text(text = "Already have an account? ")

            Text(text = " Sign In", color = Color.Blue)
        }

    }

}

@Composable
fun aff(name:String?, pwd:String?){
    Box(modifier = Modifier.fillMaxSize(),contentAlignment = Alignment.Center){
        Column() {
            Row() {
                Text(text = "Name = $name")
            }
            Row() {
                Text(text ="Password = $pwd")
            }

        }

    }
}

@Composable
fun aff2(name:String?,email:String?,pwd:String?){
    Box(modifier = Modifier.fillMaxSize(),contentAlignment = Alignment.Center){
        Column() {
            Row() {
                Text(text = "Name = $name")
            }
            Row() {
                Text(text = "Email = $email")
            }
            Row() {
                Text(text ="Password = $pwd")
            }


        }

    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview2() {
    TP5FormsComposerTheme {
        Navigation()
    }
}