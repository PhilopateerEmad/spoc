package com.raphel.spoc.data.mappers.domainAndRemoteMappers
import com.raphel.spoc.data.remote.models.LoginResponseRemoteModel
import com.raphel.spoc.domain.mapper.IMapper
import com.raphel.spoc.domain.model.LoginResponseDomainModel

class LoginResponseRemoteModelToLoginResponseDomainModelMapper : IMapper<LoginResponseRemoteModel, LoginResponseDomainModel> {
    override fun map(input: LoginResponseRemoteModel): LoginResponseDomainModel {
        return LoginResponseDomainModel(
            input.isSuccess?:false,
            input.token?:"no message",
            input.data?.toString()?:"no data",

        )


    }
}