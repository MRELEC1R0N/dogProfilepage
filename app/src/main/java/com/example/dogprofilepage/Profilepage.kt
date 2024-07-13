package com.example.dogprofilepage

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CardElevation
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.layout
import androidx.compose.ui.layout.layoutId
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.ConstraintSet
import androidx.constraintlayout.compose.Dimension


@Composable
fun ProfilePage() {
    Card(
        elevation = CardDefaults.cardElevation(defaultElevation = 6.dp),
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 100.dp, bottom = 100.dp, start = 16.dp, end = 16.dp)
            .verticalScroll(rememberScrollState())
//            .border(width = 2.dp , color = Color.Black , shape = RoundedCornerShape(30.dp))
    ) {

        BoxWithConstraints {
            val constraints = if (minWidth < 600.dp) {
                potraitConstraints(16.dp)
            } else {
                //ToDo - Landsacpe mode
                landScapeConstraints(16.dp)
            }


            ConstraintLayout(constraints)
            {
                Image(
                    painter = painterResource(id = R.drawable.husky),
                    contentDescription = null,
                    modifier = Modifier
                        .size(200.dp)
                        .clip(CircleShape)
                        .border(2.dp, Color.Red, CircleShape)
                        .layoutId("image"),
                    contentScale = ContentScale.Crop
                )
                Text(
                    text = stringResource(R.string.username),
                    modifier = Modifier.layoutId("username")
                )
                Text(
                    text = stringResource(R.string.location),
                    modifier = Modifier.layoutId("location")
                )


                Row(
                    horizontalArrangement = Arrangement.SpaceEvenly,
                    modifier = Modifier
                        .fillMaxWidth()
                        .layoutId("user_info")
                ) {
                    Userinfo(
                        num_info = stringResource(id = R.string.followers_number),
                        text_info = stringResource(id = R.string.Followers_text)
                    )

                    Userinfo(
                        num_info = stringResource(id = R.string.following_number),
                        text_info = stringResource(id = R.string.following_text)
                    )

                    Userinfo(
                        num_info = stringResource(id = R.string.post_count),
                        text_info = stringResource(id = R.string.post_string)
                    )

                }

                Button(
                    onClick = { /*TODO*/ },
                    modifier = Modifier.layoutId("follow_button")
                ) {
                    Text(text = stringResource(R.string.follow_button_text))

                }

                Button(
                    onClick = { /*TODO*/ },
                    modifier = Modifier.layoutId("message_button")
                ) {
                    Text(text = stringResource(R.string.message_button_text))
                }


            }
        }
    }
}

private fun potraitConstraints(margin:Dp): ConstraintSet {
    return ConstraintSet {
        val image = createRefFor("image")
        val username = createRefFor("username")
        val location = createRefFor("location")
        val user_info = createRefFor("user_info")
        val follow_button = createRefFor("follow_button")
        val message_button = createRefFor("message_button")

        constrain(image){
            top.linkTo(parent.top)
            start.linkTo(parent.start)
            end.linkTo(parent.end)
        }

        constrain(username){
            top.linkTo(image.bottom)
            start.linkTo(parent.start)
            end.linkTo(parent.end)
        }

        constrain(location){
            top.linkTo(username.bottom)
            start.linkTo(parent.start)
            end.linkTo(parent.end)
        }

        constrain(user_info){
            top.linkTo(location.bottom)
        }

        constrain(follow_button){
            top.linkTo(user_info.bottom , margin = margin)
            start.linkTo(parent.start)
            end.linkTo(message_button.start , margin = margin)
            width = Dimension.wrapContent
        }

        constrain(message_button){
            top.linkTo(user_info.bottom , margin = margin)
            start.linkTo(follow_button.end , margin = margin)
            end.linkTo(parent.end)
            width = Dimension.wrapContent
        }

    }
}


private fun landScapeConstraints(margin: Dp): ConstraintSet {
    return ConstraintSet {
        val image = createRefFor("image")
        val nameText = createRefFor("nameText")
        val countryText = createRefFor("countryText")
        val rowStats = createRefFor("rowstats")
        val buttonFollow = createRefFor("buttonFollow")
        val buttonMessage = createRefFor("buttonMessage")
        constrain(image){
            top.linkTo(parent.top,margin = margin)
            start.linkTo(parent.start,margin = margin)
        }
        constrain(nameText){
            start.linkTo(image.start)
            top.linkTo(image.bottom)
        }
        constrain(countryText){
            top.linkTo(nameText.bottom)
            start.linkTo(nameText.start)
            end.linkTo(nameText.end)
        }
        constrain(rowStats){
            top.linkTo(image.top)
            start.linkTo(image.end,margin = margin)
            end.linkTo(parent.end)
        }

        constrain(buttonFollow){
            top.linkTo(rowStats.bottom,margin =16.dp)
            start.linkTo(rowStats.start)
            end.linkTo(buttonMessage.start)
            bottom.linkTo(countryText.bottom)
            width = Dimension.wrapContent
        }
        constrain(buttonMessage){
            top.linkTo(rowStats.bottom,margin =16.dp)
            start.linkTo(buttonFollow.end)
            end.linkTo(parent.end)
            bottom.linkTo(countryText.bottom)
            width = Dimension.wrapContent
        }
    }
}


@Composable
fun Userinfo(num_info: String , text_info: String ) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = num_info,
            fontWeight = FontWeight.Bold
        )

        Text(
            text = text_info
        )
    }
}


//@Composable
//fun DisplayUserProfile(modifier: Modifier = Modifier) {
//
//    Image(
//        painter = painterResource(id = R.drawable.husky) ,
//        contentDescription = null,
//        modifier = Modifier
//            .size(100.dp)
//            .clip(CircleShape)
//            .border(2.dp, Color.Red, CircleShape),
//        contentScale = ContentScale.Crop
//    )
//}



//@Composable
//fun NameAndLocation(modifier: Modifier = Modifier) {
//    Text(text = stringResource(R.string.username))
//    Text(text = stringResource(R.string.location))
//}



//@Composable
//fun ProfilePageButton(modifier: Modifier = Modifier){
//
//    Row (
//        horizontalArrangement = Arrangement.SpaceEvenly,
//        modifier = Modifier
//            .fillMaxWidth()
//            .padding(16.dp)
//    ){
//        Button(onClick = { /*TODO*/ }) {
//            Text(text = stringResource(R.string.follow_button_text))
//        }
//
//        Button(onClick = { /*TODO*/ }) {
//            Text(text = stringResource(R.string.message_button_text))
//        }
//    }
//
//}



@Preview(showSystemUi = true)
@Composable
fun PreviewProfilePage(){
    ProfilePage()

}