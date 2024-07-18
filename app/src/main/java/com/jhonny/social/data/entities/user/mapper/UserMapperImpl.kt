package com.jhonny.social.data.entities.user.mapper


import com.jhonny.social.data.entities.user.entities.ResultsItem
import com.jhonny.social.data.entities.user.entities.UserResponse
import com.jhonny.social.domain.entities.Dob
import com.jhonny.social.domain.entities.DomainUser
import com.jhonny.social.domain.entities.DomainUserItem
import com.jhonny.social.domain.entities.Location
import com.jhonny.social.domain.entities.Picture
import com.jhonny.social.domain.entities.Street
import com.jhonny.social.presenter.entities.Name
import javax.inject.Inject

class UserMapperImpl @Inject constructor() : UserMapper {

    override fun responseToDomain(user: UserResponse?): DomainUser {
        return DomainUser(
            list =
            user?.results?.map {
                DomainUserItem(
                    isFavorite = it?.isFavorite?: false,
                    nat = it?.nat,
                    gender = it?.gender,
                    phone = it?.phone,
                    dob = Dob(
                        date = it?.dob?.date,
                        age = it?.dob?.age
                    ),
                    picture = Picture(
                        thumbnail = it?.picture?.thumbnail,
                        large = it?.picture?.large,
                        medium = it?.picture?.medium
                    ),
                    name = Name(
                        last = it?.name?.last,
                        first = it?.name?.first,
                        title = it?.name?.title
                    ),
                    location = Location(
                        country = it?.location?.country,
                        city = it?.location?.city,
                        street = Street(
                            number = it?.location?.street?.number,
                            name = it?.location?.street?.name
                        ),
                        postcode = it?.location?.postcode,
                    ),
                    cell = it?.cell,
                    email = it?.email
                )
            } ?: emptyList()
        )
    }

    override fun domainToResponse(user: DomainUser): UserResponse {
        return UserResponse(
            results =
            user.list?.map{
                ResultsItem(
                    isFavorite = it?.isFavorite?: false,
                    nat = it?.nat,
                    gender = it?.gender,
                    phone = it?.phone,
                    dob = com.jhonny.social.data.entities.user.entities.Dob(
                        date = it?.dob?.date,
                        age = it?.dob?.age
                    ),
                    picture = com.jhonny.social.data.entities.user.entities.Picture(
                        thumbnail = it?.picture?.thumbnail,
                        large = it?.picture?.large,
                        medium = it?.picture?.medium
                    ),
                    name = com.jhonny.social.data.entities.user.entities.Name(
                        last = it?.name?.last,
                        first = it?.name?.first,
                        title = it?.name?.title
                    ),
                    location = com.jhonny.social.data.entities.user.entities.Location(
                        country = it?.location?.country,
                        city = it?.location?.city,
                        street = com.jhonny.social.data.entities.user.entities.Street(
                            number = it?.location?.street?.number,
                            name = it?.location?.street?.name
                        ),
                        postcode = it?.location?.postcode,
                    ),
                    cell = it?.cell,
                    email = it?.email
                )
            }
            ,
            info = null
        )
    }

}
